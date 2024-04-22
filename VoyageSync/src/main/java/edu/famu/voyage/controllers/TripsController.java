package edu.famu.voyage.controllers;

import edu.famu.voyage.models.Trips;
import edu.famu.voyage.services.TripsService;
import edu.famu.voyage.util.ApiResponseFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    }
