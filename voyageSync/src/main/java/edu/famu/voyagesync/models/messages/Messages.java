package edu.famu.voyagesync.models.messages;

import com.google.firebase.database.annotations.Nullable;
import com.google.cloud.Timestamp;
import edu.famu.voyagesync.models.Users;
import edu.famu.voyagesync.models.messages.AMessages;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Messages extends AMessages {

    private Users senderID;
    private Users receiverID;

    public Messages(@Nullable String messageID, String content, Timestamp timestamp, Users senderID, Users receiverID){
        super(messageID, content, timestamp);
        this.senderID = senderID;
        this.receiverID = receiverID;
    }
}
