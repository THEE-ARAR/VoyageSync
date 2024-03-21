package edu.famu.voyagesync.controllers;

import edu.famu.voyagesync.models.Users;
import edu.famu.voyagesync.services.UsersService;
import edu.famu.voyagesync.util.ApiResponseFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService){
        this.usersService = usersService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponseFormat<List<Users>>> getAllUsers(){

        try{
            List<Users> usersList = usersService.getAllUsers();
            if (usersList.isEmpty())
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponseFormat<>(true, "No Users found.", null, null));
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Users retrieved successfully.", usersList, null));
        } catch (ExecutionException | InterruptedException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving users.", null, e.getMessage()));
        }

    }

    @GetMapping("/{user_id}")
    public ResponseEntity<ApiResponseFormat<Users>> getUserByID(@PathVariable(name = "user_id") String userID){
        try{
            Users user = usersService.getUserByID(userID);
            if (user != null){
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "User retrieved successfully", user, null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseFormat<>(false, "User not found", null, null));
            }
        } catch (ExecutionException |InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving user.", null, e.getMessage()));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponseFormat<String>> addUser(@RequestBody Users user){
        try{
//            Notification Settings
            Map<String, Boolean> notificationSettings = new HashMap<>();
            notificationSettings.put("inAppNotificationEnabled", user.isInAppNotificationEnabled());
            notificationSettings.put("pushNotificationEnabled", user.isPushNotificationEnabled());

            Map<String, List<String>> preferences = new HashMap<>();
            preferences.put("Food", user.getFoodPreferences());
            preferences.put("Activity", user.getActivityPreferences());
            preferences.put("Climate", user.getClimatePreferences());

            String userID = usersService.createUser(
                    user.getName(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getProfileImg(),
                    user.getEmergencyContactInfo(),
                    user.isLocationSharingEnabled(),
                    notificationSettings,
                    preferences
            );
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponseFormat<>(true, "User successfully created.", userID, null));
        } catch (ExecutionException | InterruptedException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error creating user.", null, e));
        }
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<String> updateUser(@PathVariable(name = "user_id") String userID,
                                             @RequestParam(required = false) String email,
                                             @RequestParam(required = false) String username,
                                             @RequestParam(required = false) String password,
                                             @RequestParam(required = false) String name,
                                             @RequestParam(required = false) String profileImg) {
        try {
            usersService.updateUser(userID, email, username, password, name, profileImg);
            return ResponseEntity.ok("User updated successfully.");
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating user: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String emailOrUsername, @RequestParam String password){
        try{
            String loginResult = usersService.loginUser(emailOrUsername, password);
            return ResponseEntity.ok(loginResult);
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred."+ e.getMessage());
        }
    }

    /* Friends List*/

//    Add Friend
    @PostMapping("/{user_id}/friends")
    public ResponseEntity<String> addFriend(@PathVariable(name = "user_id") String userID, @RequestParam String friendUsername) {
        try {
            usersService.addFriend(userID, friendUsername);
            return ResponseEntity.ok("Friend added successfully.");
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding friend: " + e.getMessage());
        }

    }
//    request friend
    @PostMapping("/{user_id}/friend-request")
    public  ResponseEntity<String> requestFriend(@PathVariable(name = "user_id") String userID, @RequestParam String friendUsername){
        try {
            usersService.requestFriend(userID, friendUsername);
            return ResponseEntity.ok("Friend request sent successfully.");
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error removing friend: " + e.getMessage());
        }
    }
//    delete friend
    @PostMapping("/{user_id}/friends")
    public ResponseEntity<String> deleteFriend(@PathVariable(name= "user_id") String userID, @RequestParam String friendUsername){
        try {
            usersService.deleteFriend(userID, friendUsername);
            return ResponseEntity.ok("Friend removed successfully.");
        } catch (ExecutionException | InterruptedException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error removing friend: " + e.getMessage());
        }
    }

}
