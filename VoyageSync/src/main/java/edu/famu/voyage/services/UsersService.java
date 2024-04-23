package edu.famu.voyage.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.voyage.RandomString;
import edu.famu.voyage.models.Users;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class UsersService {

    @JsonIgnore
    private Firestore firestore;

    private UsersService(){
        this.firestore = FirestoreClient.getFirestore();
    }

    public Users documentSnapshotToUser(DocumentSnapshot document) {
        if (document.exists()) {
            String userID = document.getId();
            String email = document.getString("email");
            String password = document.getString("password");
            String username = document.getString("username");
            String profileImg = document.getString("profileImg");
            Map<String, String> emergencyContactInfo = (Map<String, String>) document.get("emergencyContactInfo");
            Map<String, List<String>> preferences = (Map<String, List<String>>) document.get("preferences");
            boolean locationSharingEnabled = document.getBoolean("locationSharingEnabled");
            Map<String, Boolean> notificationSettings = (Map<String, Boolean>) document.get("notificationSettings");
            List<String> friendsListIds = new ArrayList<>();
            List<String> tripsIds = new ArrayList<>();

            List<DocumentReference> friendsListRefs = (List<DocumentReference>) document.get("friendsList");
            if (friendsListRefs != null) {
                for (DocumentReference friendRef : friendsListRefs){
                    friendsListIds.add(friendRef.getId());
                }
            }

            List<DocumentReference> tripsRef = (List<DocumentReference>) document.get("trips");
            if (tripsRef != null) {
                for (DocumentReference tripRef : tripsRef) {
                    tripsIds.add(tripRef.getId());
                }
            }

            return new Users(userID, email,username, password, profileImg,
                    emergencyContactInfo, preferences, locationSharingEnabled,
                    notificationSettings, friendsListIds, tripsIds);
        }
        return null;
    }

    public List<Users> getAllUsers() throws ExecutionException, InterruptedException {
        CollectionReference userCollection = firestore.collection("Users");
        ApiFuture<QuerySnapshot> future = userCollection.get();
        List<Users> usersList = new ArrayList<>();
        for (DocumentSnapshot document : future.get().getDocuments()) {
            Users user = documentSnapshotToUser(document);

            if (user != null) { usersList.add(user); }
        }
        return usersList;
    }

    public Users getUserByID(String userID) throws ExecutionException, InterruptedException {
        CollectionReference userCollection = firestore.collection("Users");
        ApiFuture<DocumentSnapshot> future = userCollection.document(userID).get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToUser(document);
    }

//    Create User
    public String createUser(Users user) throws ExecutionException, InterruptedException {
        if (user.getUserId() == null || user.getUserId().isEmpty()) {
            RandomString randomString = new RandomString(20);
            user.setUserId(randomString.nextString(20));
        }

        Firestore firestore = FirestoreClient.getFirestore();

        DocumentReference documentReference = firestore.collection("Users").document(user.getUserId());

        ApiFuture<WriteResult> result = documentReference.set(user);
        result.get().getUpdateTime().toString();
        return user.getUserId();
    }

//    Delete User
    public String deleteUser(String userId) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("Users").document(userId);
        ApiFuture<WriteResult> result = docRef.delete();
        return result.get().getUpdateTime().toString();
    }

}
