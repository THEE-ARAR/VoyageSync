package edu.famu.voyagesync.models.trips;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import edu.famu.voyagesync.util.Utility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestTrips extends ATrips{

    private DocumentReference createdBy;
    private List<DocumentReference> members;
    private List<DocumentReference> accommodations;
    private List<DocumentReference> itinerary;

    public RestTrips(String tripID, String name, String progress, double budget, double totalCost, Timestamp startDate, Timestamp endDate, DocumentReference createdBy,
                 List<DocumentReference> members, List<DocumentReference> accommodations, List<DocumentReference> itinerary){
        super(tripID, name, progress, budget, totalCost, startDate, endDate);
        this.createdBy = createdBy;
        this.members = members;
        this.accommodations = accommodations;
        this.itinerary = itinerary;
    }

    public void setUser(String createdBy){
        this.createdBy = Utility.retrieveDocumentReference("Users", createdBy);
    }

    public void setMembers(ArrayList<String> members){
        this.members = new ArrayList<>();
        for(String user : members){
            this.members.add(Utility.retrieveDocumentReference("Users", user));
        }
    }
    public void setAccommodations(ArrayList<String> accommodations){
        this.accommodations = new ArrayList<>();
        for(String accommodationID : accommodations){
            this.accommodations.add(Utility.retrieveDocumentReference("Accommodations", accommodationID));
        }
    }

    public void setItinerary(ArrayList<String> itinerary){
        this.itinerary = new ArrayList<>();
        for(String itineraryID : itinerary){
            this.itinerary.add(Utility.retrieveDocumentReference("Itinerary", itineraryID));
        }
    }


}
