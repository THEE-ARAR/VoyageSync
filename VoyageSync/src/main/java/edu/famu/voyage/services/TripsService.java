package edu.famu.voyage.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.voyage.RandomString;
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

    // Create Trip
    public String createTrip(Trips trip) throws ExecutionException, InterruptedException {
        if (trip.getTripId() == null || trip.getTripId().isEmpty()) {
            RandomString randomString = new RandomString(20);
            trip.setTripId(randomString.nextString(20));
        }
        DocumentReference tripRef = firestore.collection("Trips").document(trip.getTripId());
        ApiFuture<WriteResult> future = tripRef.set(trip);
        // Handle success or failure
        try {
            future.get();
            return trip.getTripId();
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error creating trip: " + e.getMessage());
            throw e;
        }
    }

    // Delete Trip
    public String deleteTrip(String tripId) throws ExecutionException, InterruptedException {
        DocumentReference tripRef = firestore.collection("Trips").document(tripId);
        ApiFuture<WriteResult> future = tripRef.delete();
        // Handle success or failure
        try {
            future.get();
            return "Trip deleted successfully";
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error deleting trip: " + e.getMessage());
            throw e;
        }
    }
    // Update Trip
    public String updateTrip(String tripId, Trips updatedTrip) throws ExecutionException, InterruptedException {
        DocumentReference tripRef = firestore.collection("Trips").document(tripId);
        ApiFuture<DocumentSnapshot> future = tripRef.get();
        DocumentSnapshot document = future.get();
        if (!document.exists()) {
            return "Trip with ID " + tripId + " does not exist";
        }
        ApiFuture<WriteResult> updateFuture = tripRef.set(updatedTrip);
        // Handle success or failure
        try {
            updateFuture.get();
            return "Trip updated successfully";
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error updating trip: " + e.getMessage());
            throw e;
        }
    }
}




