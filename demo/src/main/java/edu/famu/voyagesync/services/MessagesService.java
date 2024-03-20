package edu.famu.voyagesync.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.voyagesync.models.Chat;
import edu.famu.voyagesync.models.Users;
import edu.famu.voyagesync.models.messages.Messages;
import edu.famu.voyagesync.util.Utility;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class MessagesService {
    private Firestore firestore;

    public MessagesService(){
        this.firestore = FirestoreClient.getFirestore();
    }


    public Messages documentSnapshotToMessage(DocumentSnapshot document) throws ExecutionException, InterruptedException {
        if(document.exists()) {
            Users sender = null, receiver = null;

            // Retrieve sender user details
            DocumentReference senderRef = (DocumentReference) document.get("senderID");
            if (senderRef != null) {
                DocumentSnapshot userSnapshot = senderRef.get().get();
                if (userSnapshot.exists()) {
                    sender = userSnapshot.toObject(Users.class);
                }
            }

            // Retrieve receiver user details
            DocumentReference receiverRef = (DocumentReference) document.get("receiverID");
            if (receiverRef != null) {
                DocumentSnapshot userSnapshot = receiverRef.get().get();
                if (userSnapshot.exists()) {
                    receiver = userSnapshot.toObject(Users.class);
                }
            }

            return new Messages(document.getId(),
                    document.getString("content"),
                    document.getTimestamp("timestamp"),
                    sender, receiver);

        }
        return null;
    }

    public List<Messages> getMessagesBetweenUsers(String user1ID, String user2ID)
            throws ExecutionException, InterruptedException {
        List<Messages> messages = new ArrayList<>();

        ApiFuture<QuerySnapshot> user1MessagesFuture = firestore.collection("Messages")
                .whereEqualTo("senderId", Utility.retrieveDocumentReference("Users", user1ID))
                .whereEqualTo("receiverId", Utility.retrieveDocumentReference("Users", user2ID))
                .orderBy("timestamp")
                .get();

        ApiFuture<QuerySnapshot> user2MessagesFuture = firestore.collection("Messages")
                .whereEqualTo("senderId", Utility.retrieveDocumentReference("Users", user2ID))
                .whereEqualTo("receiverId", Utility.retrieveDocumentReference("Users", user1ID))
                .orderBy("timestamp")
                .get();

        // Process messages from user1 to user2
        processMessages(user1MessagesFuture.get(), messages);

        // Process messages from user2 to user1
        processMessages(user2MessagesFuture.get(), messages);

        // Sort messages in chronological order
        Collections.sort(messages, Comparator.comparing(Messages::getTimestamp));

        return messages;
    }

    private void processMessages(QuerySnapshot querySnapshot, List<Messages> messages) throws ExecutionException, InterruptedException {
        for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
            Messages message = documentSnapshotToMessage(document);
            messages.add(message);
        }
    }

    public List<Chat> getMessagesChats(String userID) throws ExecutionException, InterruptedException {
        Query querySender = firestore.collection("Messages").whereEqualTo("senderID", userID);
        Query queryReceiver = firestore.collection("Messages").whereEqualTo("receiverID", userID);
        Map<String, Chat> latestChatsMap = new HashMap<>();
        ApiFuture<QuerySnapshot> senderFuture = querySender.get();
        ApiFuture<QuerySnapshot> receiverFuture = queryReceiver.get();

        List<Chat> chats =  new ArrayList<>();

        // Process sender's chats
        processChats(senderFuture.get(), latestChatsMap, userID);

        // Process receiver's chats
        processChats(receiverFuture.get(), latestChatsMap, userID);

        // Add the latest chats to the list
        chats.addAll(latestChatsMap.values());

        return chats;
    }

    private  void processChats(QuerySnapshot querySnapshot, Map<String, Chat> latestChatsMap, String userID) throws ExecutionException, InterruptedException {
        for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
            DocumentReference senderRef =  (DocumentReference)document.get("senderID");
            Users sender = senderRef.get().get().toObject(Users.class);

            DocumentReference receiverRef =  (DocumentReference)document.get("senderID");
            Users receiver = receiverRef.get().get().toObject(Users.class);

            Chat chat = new Chat(document.getString("content"),
                    sender.getUserID(),sender.getUsername(),
                    receiver.getUserID(), receiver.getUsername(),
                    document.getTimestamp("timestamp"),
                    userID == sender.getUserID()
            );
            String otherUserID = chat.isUserSender() ? chat.getReceiverID() : chat.getSenderID();

            if (!latestChatsMap.containsKey(otherUserID) || chat.getTimestamp().compareTo(latestChatsMap.get(otherUserID).getTimestamp()) > 0) {
                chat.setIsUserSender(userID.equals(chat.getSenderID()));
                latestChatsMap.put(otherUserID, chat);
            }
        }
    }

}
