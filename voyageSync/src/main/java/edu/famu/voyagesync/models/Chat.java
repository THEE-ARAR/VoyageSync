package edu.famu.voyagesync.models;

import com.google.cloud.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    private String content;
    private String senderID;
    private String sender;
    private String receiverID;
    private String receiver;
    private Timestamp timestamp;
    private boolean isUserSender;

    public void setIsUserSender(boolean isUserSender){
        this.isUserSender = isUserSender;
    }

}
