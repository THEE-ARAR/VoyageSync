package edu.famu.voyagesync.models;

import com.google.cloud.Timestamp;
import com.google.firebase.database.annotations.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Itinerary {

    private @Nullable String itineraryID;
    private Timestamp date;
    private Timestamp time;
    private String activity;
    private String location;
    private @Nullable String notes;

}
