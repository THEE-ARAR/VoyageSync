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

        public TripsController(TripsService tripsService) {
            this.tripsService = tripsService;
        }

        @GetMapping("/")
        public ResponseEntity<ApiResponseFormat<List<Trips>>> getAllTrips() {
            try {
                List<Trips> tripsList = tripsService.getAllTrips();
                if (tripsList.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.NO_CONTENT)
                            .body(new ApiResponseFormat<>(true, "No trips found.", null, null));
                }
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "Trips retrieved successfully.", tripsList, null));
            } catch (ExecutionException | InterruptedException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ApiResponseFormat<>(false, "Error retrieving trips.", null, e.getMessage()));
            }
        }

        @GetMapping("/{trip_id}")
        public ResponseEntity<ApiResponseFormat<Trips>> getTripById(@PathVariable(name = "trip_id") String tripId) {
            try {
                Trips trip = tripsService.getTripById(tripId);
                if (trip != null) {
                    return ResponseEntity.ok(new ApiResponseFormat<>(true, "Trip retrieved successfully.", trip, null));
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(new ApiResponseFormat<>(false, "Trip not found.", null, null));
                }
            } catch (ExecutionException | InterruptedException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ApiResponseFormat<>(false, "Error retrieving trip.", null, e.getMessage()));
            }
        }
        // Create trip
        @PostMapping("/")
        public ResponseEntity<String> createTrip(@RequestBody Trips trip) {
            try {
                String tripId = tripsService.createTrip(trip);
                return new ResponseEntity<>(tripId, HttpStatus.CREATED);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        // Update trip
        @PutMapping("/{tripId}")
        public ResponseEntity<String> updateTrip(@PathVariable String tripId, @RequestBody Trips updatedTrip) {
            try {
                String message = tripsService.updateTrip(tripId, updatedTrip);
                return new ResponseEntity<>(message, HttpStatus.OK);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        // Delete trip
        @DeleteMapping("/{tripId}")
        public ResponseEntity<String> deleteTrip(@PathVariable String tripId) {
            try {
                String message = tripsService.deleteTrip(tripId);
                return new ResponseEntity<>(message, HttpStatus.OK);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
}
