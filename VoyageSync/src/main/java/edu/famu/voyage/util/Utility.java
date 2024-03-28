package edu.famu.voyage.util;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

public class Utility {
    public static final String DEFAULT_MEDIA_TYPE = "application/json";
    public static DocumentReference retrieveDocumentReference(String collection, String id) {
        Firestore db = FirestoreClient.getFirestore();
        return db.collection(collection).document(id);
    }
}
