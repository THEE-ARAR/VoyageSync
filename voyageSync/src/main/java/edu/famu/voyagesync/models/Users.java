package edu.famu.voyagesync.models;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.firebase.database.annotations.Nullable;
import edu.famu.voyagesync.models.trips.Trips;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @DocumentId
    private @Nullable String userID;
    private String name;
    private String username;
    private String email;
    private String password;
    private String profileImg;
    private String emergencyContactInfo;
    private List<String> friendsList;
    private Map<String, Boolean> notificationSetting;
    private boolean locationSharingEnabled;
    private Map<String, List<String>> preferences;
    private List<Trips> trips;
    private boolean inAppNotificationEnabled;
    private boolean isPushNotificationEnabled;
    private List<String> foodPreferences;
    private List<String> activityPreferences;
    private List<String> climatePreferences;




}
