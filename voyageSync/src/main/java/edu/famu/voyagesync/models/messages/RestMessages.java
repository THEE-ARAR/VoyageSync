package edu.famu.voyagesync.models.messages;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.firebase.database.annotations.Nullable;
import edu.famu.voyagesync.models.messages.AMessages;
import edu.famu.voyagesync.util.Utility;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RestMessages extends AMessages {

    private DocumentReference senderID;
    private DocumentReference receiverID;

    public RestMessages(@Nullable String messageID, String content, Timestamp timestamp, DocumentReference senderID, DocumentReference receiverID){
        super(messageID, content, timestamp);
        this.senderID = senderID;
        this.receiverID = receiverID;
    }

    public void setSenderID(String senderID){
        this.senderID = Utility.retrieveDocumentReference("Users", senderID);
    }

    public void setReceiverID(String receiverID){
        this.receiverID = Utility.retrieveDocumentReference("Users", receiverID);
    }

}
