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

public class Accommodations {
    @DocumentId
    private String accommodationId;
    private String name;
    private Timestamp checkInDate;
    private Timestamp checkOutDate;
    private GeoPoint location;

}
