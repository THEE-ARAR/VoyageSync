package edu.famu.voyage.controllers;

import edu.famu.voyage.models.Users;
import edu.famu.voyage.services.UsersService;
import edu.famu.voyage.util.ApiResponseFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) { this.usersService = usersService; }

    @GetMapping("/")
    public ResponseEntity<ApiResponseFormat<List<Users>>> getAllUsers(){
        try{
            List<Users> usersList = usersService.getAllUsers();
            if (usersList.isEmpty()){
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(new ApiResponseFormat<>(true, "No Users found.", null, null));
            }
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Users retrieve successfully.", usersList, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving users.", null, e.getMessage()));
        }

    }

    @GetMapping("/{user_id}")
    public ResponseEntity<ApiResponseFormat<Users>> getUserById(@PathVariable(name = "user_id") String userId) throws ExecutionException, InterruptedException{
        try {
            Users user = usersService.getUserByID(userId);
            if (user != null) {
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "User retrieved successfully.", user, null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseFormat<>(false, "User not found.", null, null));
            }
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving user.", null, e.getMessage()));
        }
    }

//    Create User
    @PostMapping("/create/")
    public ResponseEntity<ApiResponseFormat<String>> createUser(@RequestBody Users user){
        try {
            String result = usersService.createUser(user);
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "User created successfully.", result, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error creating user.", null, e.getMessage()));
        }
    }

//    Delete User
    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity<ApiResponseFormat<String>> deleteUser(@PathVariable("user_id") String userId) {
        try {
            String result = usersService.deleteUser(userId);
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "User deleted successfully.", result, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error deleting user.", null, e.getMessage()));
        }
    }

}
