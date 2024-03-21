package edu.famu.voyagesync.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.voyagesync.models.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /* Authentication */

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
    //    Login User
    public String loginUser(String emailOrUsername, String password) throws ExecutionException, InterruptedException{
        String field = identifyField(emailOrUsername);
        CollectionReference userCollection = firestore.collection("Users");
        DocumentSnapshot userSnapshot = null;

        if (field.equals("email")) {
            userSnapshot = userCollection.whereEqualTo("email", emailOrUsername)
                    .get().get().getDocuments().get(0);
        } else if (field.equals("username")) {
            userSnapshot = userCollection.whereEqualTo("username", emailOrUsername)
                    .get().get().getDocuments().get(0);
        }

        if (userSnapshot != null) {
            String storedPassword = userSnapshot.getString("password");
            assert storedPassword != null;
            if (storedPassword.equals(password)) {
                return "User logged in successfully.";
            } else {
                return "Incorrect password.";
            }
        } else {
            return "User not found.";
        }
    }
    //    Update user
    public WriteResult updateUser(String userID, String email, String password, String name, String username, String profileImg)
            throws ExecutionException, InterruptedException{

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

//    User input verification
    private String identifyField(String emailOrUsername){
        if (emailOrUsername.contains("@")){return "email";}
        return "username";
    }

    /* Friend List */
//    add friend
    public void addFriend(String userID, String friendUsername) throws ExecutionException, InterruptedException{
        DocumentReference friendRef = firestore.collection("Users").whereEqualTo("username", friendUsername).limit(1).get().get().getDocuments().get(0).getReference();
        DocumentSnapshot friendSnapshot = friendRef.get().get();

        if (friendSnapshot.exists()) {
            List<String> friendsList = getUserFriendsList(userID);

            if(!friendsList.contains(friendSnapshot.getId())) {
                friendsList.add(friendSnapshot.getId());
                updateUserFriendsList(userID, friendsList);
            } else { throw new IllegalStateException("User is already in your friend list."); }
        } else { throw new IllegalArgumentException("Username does not exist."); }
    }
//    request friend
    public void requestFriend(String userID, String friendUsername) throws ExecutionException, InterruptedException {
        DocumentReference friendRef = firestore.collection("Users").whereEqualTo("username", friendUsername).limit(1).get().get().getDocuments().get(0).getReference();
        DocumentSnapshot friendSnapshot = friendRef.get().get();

        if (friendSnapshot.exists()){
            List<String> friendsList = getUserFriendsList(userID);

            if (!friendsList.contains(friendSnapshot.getId())) {
                friendsList.add((friendSnapshot.getId()));
                updateUserFriendsList(userID, friendsList);

                sendFriendRequestNotification(friendSnapshot.getId(), userID);
            } else { throw new IllegalStateException("User is already in your friend list"); }
        } else { sendInvitationLinkToFriend(userID, friendUsername); }
    }
    //    delete friend
    public void deleteFriend(String userID, String friendUsername) throws ExecutionException, InterruptedException {
        DocumentReference friendRef = firestore.collection("Users").whereEqualTo("username", friendUsername).limit(1).get().get().getDocuments().get(0).getReference();
        DocumentSnapshot friendSnapshot = friendRef.get().get();

        if (friendSnapshot.exists()) {
            List<String> friendsList = getUserFriendsList(userID);

            if (friendsList.contains(friendSnapshot.getId())) {
                friendsList.remove(friendSnapshot.getId());

                updateUserFriendsList(userID, friendsList);
            } else { throw new IllegalStateException("User is not in your friend list."); }
        } else { throw new IllegalArgumentException("Username does not exist."); }
    }


//    update friendList
    private void updateUserFriendsList(String userID, List<String> friendsList) throws ExecutionException, InterruptedException {
        firestore.collection("Users").document(userID).update("friendsList", friendsList);

    }
//    get user friendList
    private List<String> getUserFriendsList(String userID) throws ExecutionException, InterruptedException {
        DocumentSnapshot userSnapshot = firestore.collection("Users").document(userID).get().get();
        return Objects.requireNonNull(userSnapshot.toObject(Users.class)).getFriendsList();
    }
//    send friend request notification
    private void sendFriendRequestNotification(String friendID,  String userID) throws ExecutionException, InterruptedException{}
//    send invitation link to friend number
    private void sendInvitationLinkToFriend(String userID, String friendPhoneNumber){}




}
