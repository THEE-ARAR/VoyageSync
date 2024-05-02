package edu.famu.voyage.models;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import edu.famu.taskmanager.models.Users;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;


@Data
@NoArgsConstructor
public class Messages extends edu.famu.taskmanager.models.messages.AMessages {
    private Users senderId;
    private Users receiverId;

    public Messages(@Nullable String messageId, String content, Timestamp timestamp, Users senderId, Users receiverId) {
        super(messageId, content, timestamp);
        this.senderId = senderId;
        this.receiverId = receiverId;
    }
}