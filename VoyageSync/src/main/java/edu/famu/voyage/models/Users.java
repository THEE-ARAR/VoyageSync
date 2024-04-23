package edu.famu.voyage.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"clock"})
public class Users {

    // User Info
    @DocumentId
    private String userId;
    private String email;
    private String password;
    private String username;
    private String profileImg;
    private Map<String, String> emergencyContactInfo;
    private Map<String, List<String>> preferences;

    // Notifications
    private boolean locationSharingEnabled;
    private Map<String, Boolean> notificationSettings;

    private List<String> friendsListIds;
    private List<String> tripsIds;

}
