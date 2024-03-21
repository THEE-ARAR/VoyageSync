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
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Users retrieved sucessfully.", usersList, null));
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
                    user.getPreferences()
            );
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponseFormat<>(true, "User sucessfully created.", userID, null));
        } catch (ExecutionException | InterruptedException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error creating user.", null, e));
        }
    }

}
