public class MessagesService {

    private Firestore firestore;

    public MessagesService(){
        this.firestore = FirestoreClient.getFirestore();
    }


    public Messages documentSnapshotToMessage(DocumentSnapshot document) throws ExecutionException, InterruptedException {
        if(document.exists()) {
            Users sender = null, receiver = null;

            // Retrieve sender user details
            DocumentReference senderRef = (DocumentReference) document.get("senderId");
            if (senderRef != null) {
                DocumentSnapshot userSnapshot = senderRef.get().get();
                if (userSnapshot.exists()) {
                    sender = userSnapshot.toObject(Users.class);
                }
            }

            // Retrieve receiver user details
            DocumentReference receiverRef = (DocumentReference) document.get("receiverId");
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

    public List<Messages> getMessagesBetweenUsers(String user1Id, String user2Id)
            throws ExecutionException, InterruptedException {
        List<Messages> messages = new ArrayList<>();

        ApiFuture<QuerySnapshot> user1MessagesFuture = firestore.collection("Messages")
                .whereEqualTo("senderId", Utility.retrieveDocumentReference("Users", user1Id))
                .whereEqualTo("receiverId", Utility.retrieveDocumentReference("Users", user2Id))
                .orderBy("timestamp")
                .get();

        ApiFuture<QuerySnapshot> user2MessagesFuture = firestore.collection("Messages")
                .whereEqualTo("senderId", Utility.retrieveDocumentReference("Users", user2Id))
                .whereEqualTo("receiverId", Utility.retrieveDocumentReference("Users", user1Id))
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

    public List<Chat> getMessagesChats(String userId) throws ExecutionException, InterruptedException {
        Query querySender = firestore.collection("Messages").whereEqualTo("senderId", userId);
        Query queryReceiver = firestore.collection("Messages").whereEqualTo("receiverId", userId);
        Map<String, Chat> latestChatsMap = new HashMap<>();
        ApiFuture<QuerySnapshot> senderFuture = querySender.get();
        ApiFuture<QuerySnapshot> receiverFuture = queryReceiver.get();

        List<Chat> chats =  new ArrayList<>();

        // Process sender's chats
        processChats(senderFuture.get(), latestChatsMap, userId);

        // Process receiver's chats
        processChats(receiverFuture.get(), latestChatsMap, userId);

        // Add the latest chats to the list
        chats.addAll(latestChatsMap.values());

        return chats;
    }

    private  void processChats(QuerySnapshot querySnapshot, Map<String, Chat> latestChatsMap, String userId) throws ExecutionException, InterruptedException {
        for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
            DocumentReference senderRef =  (DocumentReference)document.get("senderId");
            Users sender = senderRef.get().get().toObject(Users.class);

            DocumentReference receiverRef =  (DocumentReference)document.get("senderId");
            Users receiver = receiverRef.get().get().toObject(Users.class);

            Chat chat = new Chat(document.getString("content"),
                    sender.getUserId(),sender.getUsername(),
                    receiver.getUserId(), receiver.getUsername(),
                    document.getTimestamp("timestamp"),
                    userId == sender.getUserId()
            );
            String otherUserId = chat.isUserSender() ? chat.getReceiverId() : chat.getSenderId();

            if (!latestChatsMap.containsKey(otherUserId) || chat.getTimestamp().compareTo(latestChatsMap.get(otherUserId).getTimestamp()) > 0) {
                chat.setIsUserSender(userId.equals(chat.getSenderId()));
                latestChatsMap.put(otherUserId, chat);
            }
        }
    }

}