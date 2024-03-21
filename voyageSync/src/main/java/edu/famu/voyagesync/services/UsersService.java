package edu.famu.voyagesync.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.voyagesync.models.Users;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class UsersService {

    private Firestore firestore;


    public UsersService(){
        this.firestore = FirestoreClient.getFirestore();

    }

    public Users documentSnapshotToUser(DocumentSnapshot document) {
        if (document.exists()) {
            return document.toObject(Users.class);
        }
        return null;
    }

    public List<Users> getAllUsers() throws ExecutionException, InterruptedException {
        CollectionReference userCollection = firestore.collection("Users");
        ApiFuture<QuerySnapshot> future = userCollection.get();
        List<Users> userList = new ArrayList<>();
        for (DocumentSnapshot document : future.get().getDocuments()) {
            Users user = documentSnapshotToUser(document);
            if (user != null) {
                userList.add(user);
            }
        }
        return userList;
    }

    public Users getUserByID(String userID) throws ExecutionException, InterruptedException {
        CollectionReference userCollection = firestore.collection("Users");
        ApiFuture<DocumentSnapshot> future = userCollection.document(userID).get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToUser(document);
    }

//    create user
    public String createUser(String name, String username, String email, String password, String profileImg,
                           String emergencyContactInfo, boolean locationSharingEnabled, Map<String, Boolean> notificationSetting,
                           Map<String, List<String>> preferences) throws ExecutionException, InterruptedException {

        CollectionReference usersCollection = firestore.collection("Users");

        Users newUser = new Users();
        newUser.setName(name);
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setProfileImg(profileImg);
        newUser.setEmergencyContactInfo(emergencyContactInfo);
        newUser.setNotificationSetting(notificationSetting);
        newUser.setLocationSharingEnabled(locationSharingEnabled);
        newUser.setPreferences(preferences);

        ApiFuture<DocumentReference> future = usersCollection.add(newUser);
        DocumentReference docRef = future.get();

        return docRef.getId();

    }

    public WriteResult updateUser(String userID, String email, String password, String name, String username, String profileImg) throws ExecutionException, InterruptedException{

        String[] allowed = {"email", "username", "password", "name", "profileImg"};
        List<String>  allowedFields = Arrays.asList(allowed);
        Map<String, Object> updateValues = new HashMap<>();

        if (email != null & allowedFields.contains("email")){updateValues.put("email", email);}
        if (username != null & allowedFields.contains("username")){updateValues.put("username", username);}
        if (password != null & allowedFields.contains("password")){updateValues.put("password", password);}
        if (name != null & allowedFields.contains("name")){updateValues.put("name", name);}
        if(profileImg != null & allowedFields.contains("profileImg")){updateValues.put("profileImg", profileImg);}

        DocumentReference userDoc = firestore.collection("Users").document(userID);
        ApiFuture<WriteResult> result = userDoc.update(updateValues);
        return result.get();
    }
}
