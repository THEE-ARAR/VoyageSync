package edu.famu.voyage.models;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.GeoPoint;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Messages extends AMessages {

    private Users senderId;
    private Users receiverId;

    public Messages(@Nullable String messageId, String content, Timestamp timestamp, Users senderId, Users receiverId) {
        super(messageId, content, timestamp);
        this.senderId = senderId;
        this.receiverId = receiverId;
    }
}
