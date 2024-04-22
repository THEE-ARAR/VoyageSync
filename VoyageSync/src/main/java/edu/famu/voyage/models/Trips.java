package edu.famu.voyage.models;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.GeoPoint;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Trips {
    @DocumentId
    private String tripId;
    private String name;
    private Number budget;
    private Number totalCost;
    private Number progress;
    private Timestamp startDate;
    private Timestamp endDate;
    private Timestamp updatedDate;
    private boolean isComplete;
    private GeoPoint location;

    // References
    private DocumentReference createdBy;
    private List<DocumentReference> members;
    private DocumentReference accommodations;
    private DocumentReference itinerary;

}
