package edu.famu.voyage.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.voyage.models.Trips;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class TripsService {

    private final Firestore firestore;

    public TripsService() {
        this.firestore = FirestoreClient.getFirestore();
    }

    public Trips documentSnapshotToTrip(DocumentSnapshot document) {
        if (document.exists()) {
            // Extract trip data from the document and create a Trips instance
            return new Trips(
                    document.getId(),
                    document.getString("name"),
                    document.getDouble("budget"),
                    document.getDouble("totalCost"),
                    document.getDouble("progress"),
                    document.getTimestamp("startDate"),
                    document.getTimestamp("endDate"),
                    document.getTimestamp("updatedDate"),
                    document.getBoolean("isComplete"),
                    document.getGeoPoint("location"),
                    // You may need to handle document references here
                    null, // createdBy
                    null, // members
                    null, // accommodations
                    null  // itinerary
            );
        }
        return null;
    }

    public List<Trips> getAllTrips() throws ExecutionException, InterruptedException {
        CollectionReference tripsCollection = firestore.collection("Trips");
        ApiFuture<QuerySnapshot> future = tripsCollection.get();
        List<Trips> tripsList = new ArrayList<>();
        for (DocumentSnapshot document : future.get().getDocuments()) {
            Trips trip = documentSnapshotToTrip(document);
            if (trip != null) {
                tripsList.add(trip);
            }
        }
        return tripsList;
    }

    public Trips getTripById(String tripId) throws ExecutionException, InterruptedException {
        DocumentReference tripRef = firestore.collection("Trips").document(tripId);
        ApiFuture<DocumentSnapshot> future = tripRef.get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToTrip(document);
    }

    // Implement methods for creating, updating, and deleting trips
    // public void createTrip(Trips trip) { }
    // public void updateTrip(String tripId, Trips updatedTrip) { }
    // public void deleteTrip(String tripId) { }
}
