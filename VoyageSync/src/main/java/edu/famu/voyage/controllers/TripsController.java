package edu.famu.voyage.controllers;

import edu.famu.voyage.models.Trips;
import edu.famu.voyage.services.TripsService;
import edu.famu.voyage.util.ApiResponseFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/trips")
public class TripsController {

    private final TripsService tripsService;

    public TripsController(TripsService tripsService) { this.tripsService = tripsService; }


    @GetMapping("/")
    public ResponseEntity<ApiResponseFormat<List<Trips>>> getAllTrips(){
        try{
            List<Trips> tripsList = tripsService.getAllTrips();
            if (tripsList.isEmpty()){
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(new ApiResponseFormat<>(true, "No Users found.", null, null));
            }
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Users retrieve successfully.", tripsList, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving users.", null, e.getMessage()));
        }

    }

    @GetMapping("/{trip_id}")
    public ResponseEntity<ApiResponseFormat<Trips>> getUserById(@PathVariable(name = "trip_id") String tripId) throws ExecutionException, InterruptedException{
        try {
            Trips trip = tripsService.getTripById(tripId);
            if (trip != null) {
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "User retrieved successfully.", trip, null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseFormat<>(false, "User not found.", null, null));
            }
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving user.", null, e.getMessage()));
        }
    }

    //    Create Trip
    @PostMapping("/create/")
    public ResponseEntity<ApiResponseFormat<String>> createTrip(@RequestBody Trips trip){
        try {
            String result = tripsService.createTrip(trip);
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Trip created successfully.", result, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error creating trip.", null, e.getMessage()));
        }
    }


}
