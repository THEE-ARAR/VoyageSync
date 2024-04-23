package edu.famu.voyage.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
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

    private Firestore firestore;

    public TripsService() {
        this.firestore = FirestoreClient.getFirestore();
    }

    public Trips documentSnapshotToTrip(DocumentSnapshot document){
        if (document.exists()) {
            String tripId = document.getId();
            String name = document.getString("name");
            GeoPoint location = document.getGeoPoint("location");
            Double progress = document.getDouble("progress");
            Double totalCost = document.getDouble("totalCost");
            Double budget = document.getDouble("budget");
            Boolean isComplete = document.getBoolean("isComplete");

            // References
            DocumentReference createdByRef = document.get("createdBy", DocumentReference.class);
            String createdBy = createdByRef != null ? createdByRef.getId() : null;

            List<String> membersListIds = new ArrayList<>();
            List<DocumentReference> membersListRefs = (List<DocumentReference>) document.get("members");
            if (membersListRefs != null) {
                for (DocumentReference membersRefs : membersListRefs) {
                    membersListIds.add(membersRefs.getId());
                }
            }

            DocumentReference accommodationsRef = document.get("accommodations", DocumentReference.class);
            String accommodations = accommodationsRef != null ? accommodationsRef.getId() : null;

            List<String> itineraryListIds = new ArrayList<>();
            List<DocumentReference> itineraryListRefs = (List<DocumentReference>) document.get("itinerary");
            if (itineraryListRefs != null) {
                for (DocumentReference itineraryRefs : itineraryListRefs) {
                    itineraryListIds.add(itineraryRefs.getId());
                }
            }

            // Timestamps
            Timestamp startDate = document.getTimestamp("startDate");
            Timestamp endDate = document.getTimestamp("endDate");
            Timestamp updatedAt = document.getTimestamp("updatedAt");

            return new Trips(tripId, name, location, progress, totalCost, budget, isComplete, createdBy, membersListIds,
                    accommodations, itineraryListIds, startDate, endDate, updatedAt);

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

    public String createTrip(Trips trip) throws ExecutionException, InterruptedException {
        if (trip.getTripId() == null || trip.getTripId().isEmpty()) {
            RandomString randomString = new RandomString(20);
            trip.setTripId(randomString.nextString(20));
        }

        Firestore firestore = FirestoreClient.getFirestore();

        DocumentReference documentReference = firestore.collection("Trips").document(trip.getTripId());

        ApiFuture<WriteResult> result = documentReference.set(trip);
        result.get().getUpdateTime().toString();
        return trip.getTripId();
    }


}
