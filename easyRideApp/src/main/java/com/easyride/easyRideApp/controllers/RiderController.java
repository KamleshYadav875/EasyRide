package com.easyride.easyRideApp.controllers;

import com.easyride.easyRideApp.dto.RatingDto;
import com.easyride.easyRideApp.dto.RideDto;
import com.easyride.easyRideApp.dto.RideRequestDto;
import com.easyride.easyRideApp.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rider")
@RequiredArgsConstructor
@Secured("ROLE_RIDER")
public class RiderController {


    private final RiderService riderService;

    @PostMapping("/requestride")
    public ResponseEntity<RideRequestDto> requestRide(@RequestBody RideRequestDto rideRequestDto){
        return ResponseEntity.ok(riderService.requestRide(rideRequestDto));

    }

    @PostMapping("/rateDriver")
    public ResponseEntity<RatingDto> endRide(@RequestBody RatingDto ratingDto){
        return ResponseEntity.ok(riderService.rateDriver(ratingDto));
    }

    @PostMapping("/cancelRide/{rideRequestId}")
    public ResponseEntity<RideRequestDto> cancelRide(@PathVariable Long rideRequestId){
        return ResponseEntity.ok(riderService.cancelRideRequest(rideRequestId));
    }
}
