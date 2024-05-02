package edu.famu.voyage.controllers;

import edu.famu.voyage.models.Messages;
import edu.famu.voyage.services.MessageService;
import edu.famu.voyage.util.ApiResponseFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
@RestController
@RequestMapping("/api")
public class MessagesController {

    private final MessagesService messagesService;

    public MessagesController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @GetMapping("/messages/between/{user1Id}/{user2Id}")
    public ResponseEntity<ApiResponseFormat<List<Messages>>> getMessagesBetweenUsers(
            @PathVariable String user1Id,
            @PathVariable String user2Id)  {
        try {
            List<Messages> messagesList = messagesService.getMessagesBetweenUsers(user1Id, user2Id);
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Success", messagesList, null));
        } catch (ExecutionException | InterruptedException e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseFormat(true, "Unable to get messages.", null, e));
        }

    }

    @GetMapping("/chats/{userId}")
    public ResponseEntity<ApiResponseFormat<List<Chat>>> getMessagesChats(@PathVariable String userId)  {
        try {
            List<Chat> chats = messagesService.getMessagesChats(userId);
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Success", chats, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseFormat(true, "Unable to get chats.", null, e));
        }
    }
}