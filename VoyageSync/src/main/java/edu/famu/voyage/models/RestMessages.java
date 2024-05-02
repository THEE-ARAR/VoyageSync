package edu.famu.voyage.models;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.firebase.database.annotations.Nullable;
import com.google.protobuf.util.Timestamps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestMessages extends AMessages{

    private DocumentReference senderId;
    private DocumentReference receiverId;

    public RestMessages(@Nullable String messageId, String content, Timestamp timestamp, DocumentReference senderId, DocumentReference receiverId) {
        super(messageId, content, timestamp);
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public void setSenderId(String senderId) {
        this.senderId = Utility.retrieveDocumentReference("Users", senderId);
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = Utility.retrieveDocumentReference("Users", receiverId);
    }

}