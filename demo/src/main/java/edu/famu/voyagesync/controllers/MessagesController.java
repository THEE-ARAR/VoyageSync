package edu.famu.voyagesync.controllers;

import edu.famu.voyagesync.models.Chat;
import edu.famu.voyagesync.models.messages.Messages;
import edu.famu.voyagesync.services.MessagesService;
import edu.famu.voyagesync.util.ApiResponseFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class MessagesController {

    private final MessagesService messagesService;

    public MessagesController(MessagesService messagesService){this.messagesService = messagesService;}

    @GetMapping("/messages/between/{user1ID}/{user2ID}")
    public ResponseEntity<ApiResponseFormat<List<Messages>>> getMessagesBetweenUsers(@PathVariable String user1ID, @PathVariable String user2ID){
        try {
            List<Messages> messagesList = messagesService.getMessagesBetweenUsers(user1ID,user2ID);
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Success", messagesList, null));
        } catch (ExecutionException | InterruptedException e ) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(true, "Unable to get messages.", null, e));
        }
    }

    @GetMapping("/chats/{userID}")
    public ResponseEntity<ApiResponseFormat<List<Chat>>> getMessagesChats(@PathVariable String userID){
        try {
            List<Chat> chats = messagesService.getMessagesChats(userID);
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Success", chats, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(true, "Unable to get chats.", null, e));
        }
    }
    
}
