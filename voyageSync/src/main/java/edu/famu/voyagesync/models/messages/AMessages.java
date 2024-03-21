package edu.famu.voyagesync.models.messages;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.firebase.database.annotations.Nullable;
import com.google.protobuf.util.Timestamps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;


@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AMessages {

    @DocumentId
    protected @Nullable String messageID;
    protected String contact;
    protected Timestamp timestamp;

    public void setTimeStamp(String timestamp) throws ParseException {

        this.timestamp = Timestamp.fromProto(Timestamps.parse(timestamp));

    }

}
