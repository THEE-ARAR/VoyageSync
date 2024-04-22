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

public class Itinerary {
    @DocumentId
    private String itineraryId;
    private String activity;
    private Timestamp dateTime;
    private GeoPoint location;
    private String notes;

}
