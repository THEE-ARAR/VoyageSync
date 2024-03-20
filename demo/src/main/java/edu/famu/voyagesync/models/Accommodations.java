package edu.famu.voyagesync.models;

import com.google.cloud.Timestamp;
import com.google.firebase.database.annotations.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Accommodations {

    private @Nullable String accommodationID;
    private String name;
    private String location;
    private Timestamp checkInDate;
    private Timestamp checkOutDate;

}
