package edu.famu.voyagesync.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.annotations.Nullable;
import edu.famu.voyagesync.models.Accommodations;
import edu.famu.voyagesync.models.Itinerary;
import edu.famu.voyagesync.models.Users;
import edu.famu.voyagesync.models.trips.Trips;
import edu.famu.voyagesync.util.Utility;
import org.apache.catalina.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TripsService {
    private Firestore firestore;

    public TripsService(){
        this.firestore = FirestoreClient.getFirestore();
    }

    @Nullable
    private Trips documentSnapshotToTrip(DocumentSnapshot document) throws ExecutionException, InterruptedException {
        if (document.exists()) {
            Users createdBy = null;
            List<Users> members = new ArrayList<>();
            List<Itinerary> itinerary = new ArrayList<>();
            List<Accommodations> accommodations = new ArrayList<>();

            DocumentReference createdByRef = (DocumentReference) document.get("createdBy");
            if (createdByRef != null) {
                DocumentSnapshot createdBySnapshot = createdByRef.get().get();
                if (createdBySnapshot.exists()) {
                    createdBy = createdBySnapshot.toObject(Users.class);
                }
            }

            // Retrieve member details
            List<DocumentReference> memberRefs = (List<DocumentReference>) document.get("members");
            for(DocumentReference memberRef : memberRefs) {
                DocumentSnapshot memberSnapshot = memberRef.get().get();
                if (memberSnapshot.exists()) {
                    members.add(memberSnapshot.toObject(Users.class));
                }
            }

            // Retrieve itinerary details
            List<DocumentReference> itineraryIDs = (List<DocumentReference>) document.get("itinerary");
            for(DocumentReference itineraryID : itineraryIDs){
                DocumentReference itineraryRef = firestore.collection("Itinerary").document(String.valueOf(itineraryID));
                DocumentSnapshot itinerarySnapshot = itineraryRef.get().get();
                if (itinerarySnapshot.exists()){
                    itinerary.add(itinerarySnapshot.toObject(Itinerary.class));
                }
            }
            // Retrieve accommodation details
            List<DocumentReference> accommodationIDs = (List<DocumentReference>) document.get("accommodations");
            for(DocumentReference accommodationID : accommodationIDs){
                DocumentReference accommodationRef = firestore.collection("Accommodations").document(String.valueOf(accommodationID));
                DocumentSnapshot accommodationSnapshot = accommodationRef.get().get();
                if (accommodationSnapshot.exists()){
                    accommodations.add(accommodationSnapshot.toObject(Accommodations.class));
                }
            }

            return new Trips(
                    document.getId(),
                    document.getString("name"),
                    document.getString("progress"),
                    document.getDouble("budget"),
                    document.getDouble("totalCost"),
                    document.getTimestamp("startDate"),
                    document.getTimestamp("endDate"),
                    createdBy,
                    members,
                    accommodations,
                    itinerary


            );
        }
        return null;
    }

    @Nullable
    private List<Trips> getTripList(Query query) throws InterruptedException, ExecutionException {
        ApiFuture<QuerySnapshot> future = query.get();
        List<QueryDocumentSnapshot> documentSnapshots = future.get().getDocuments();
        List<Trips> tripList = documentSnapshots.size() == 0 ? null : new ArrayList<>();

        for(DocumentSnapshot document : documentSnapshots) {
            tripList.add(documentSnapshotToTrip(document));
        }

        return tripList;
    }

    public List<Trips> getAllTrips() throws ExecutionException, InterruptedException {
        CollectionReference tripsCollection = firestore.collection("Trips");
        return getTripList(tripsCollection);
    }

    public Trips getTripById(String tripId) throws ExecutionException, InterruptedException {
        DocumentReference tripRef = firestore.collection("Trips").document(tripId);
        return documentSnapshotToTrip(tripRef.get().get());
    }


}
