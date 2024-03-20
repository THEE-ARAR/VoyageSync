package edu.famu.voyagesync.models.trips;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.firebase.database.annotations.Nullable;
import com.google.protobuf.util.Timestamps;
import edu.famu.voyagesync.models.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class ATrips {
    @DocumentId
    private @Nullable String tripID;
    private String name;
    private String progress;
    private double budget;
    private double totalCost;
    private Timestamp startDate;
    private Timestamp endDate;

    public void setStartDate(String startDate) throws ParseException{
        this.startDate = Timestamp.fromProto(Timestamps.parse(startDate));
    }

    public void setEndDate(String endDate) throws ParseException{
        this.endDate = Timestamp.fromProto(Timestamps.parse(endDate));
    }
}
