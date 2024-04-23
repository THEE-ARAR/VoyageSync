package edu.famu.voyage.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.GeoPoint;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.protobuf.util.Timestamps;
import edu.famu.voyage.GeoPointDeserializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.util.List;

@Data
@NoArgsConstructor
public class Trips {

    @DocumentId
    private String tripId;
    private String name;
    @JsonDeserialize(using = GeoPointDeserializer.class)
    private GeoPoint location;
    private Double progress;
    private Double totalCost;
    private Double budget;
    private Boolean isComplete;

//    References
    private String createdBy;
    private List<String> members;
    private String accommodations;
    private List<String> itinerary;

//    Timestamps
    private Timestamp startDate;
    private Timestamp endDate;
    private Timestamp updatedAt;



    public Trips(String tripId, String name, GeoPoint location, Double progress, Double totalCost,
                 Double budget, Boolean isComplete, String createdBy, List<String> members, String accommodations,
                 List<String> itinerary, Timestamp startDate, Timestamp endDate, Timestamp updatedAt){
        this.tripId = tripId;
        this.name = name;
        this.location = location;
        this.progress = progress;
        this.totalCost = totalCost;
        this.budget = budget;
        this.isComplete = isComplete;
        this.createdBy = createdBy;
        this.members = members;
        this.accommodations = accommodations;
        this.itinerary = itinerary;
        this.startDate = startDate;
        this.endDate = endDate;
        this.updatedAt = updatedAt;
    }


    public void setStartDate(String startDate) throws ParseException {
        this.startDate = Timestamp.fromProto(Timestamps.parse(startDate));

    }
    public void updateStartDate(Timestamp startDate){
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) throws ParseException {
        this.endDate = Timestamp.fromProto(Timestamps.parse(endDate));

    }
    public void updateEndDate(Timestamp endDate){
        this.endDate = endDate;
    }

    public void setUpdatedAt(String updatedAt) throws ParseException {
        this.updatedAt = Timestamp.fromProto(Timestamps.parse(updatedAt));

    }
    public void updateUpdatedAt(Timestamp updatedAt){
        this.updatedAt = updatedAt;
    }


}
