package edu.famu.voyagesync.models.trips;


import com.google.cloud.Timestamp;
import edu.famu.voyagesync.models.Accommodations;
import edu.famu.voyagesync.models.Itinerary;
import edu.famu.voyagesync.models.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trips extends ATrips {

    private Users createdBy;
    private List<Users> members;
    private List<Accommodations> accommodations;
    private List<Itinerary> itinerary;

    public Trips(String tripID, String name, String progress, double budget, double totalCost, Timestamp startDate, Timestamp endDate, Users createdBy,
                 List<Users> members, List<Accommodations> accommodations, List<Itinerary> itinerary){
        super(tripID, name, progress, budget, totalCost, startDate, endDate);
        this.createdBy = createdBy;
        this.members = members;
        this.accommodations = accommodations;
        this.itinerary = itinerary;
    }

}
