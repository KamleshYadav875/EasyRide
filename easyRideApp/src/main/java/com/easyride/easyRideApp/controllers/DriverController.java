package com.easyride.easyRideApp.controllers;

import com.easyride.easyRideApp.dto.RatingDto;
import com.easyride.easyRideApp.dto.RideDto;
import com.easyride.easyRideApp.dto.StartRideDto;
import com.easyride.easyRideApp.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping("/acceptRide/{rideRequestId}")
    public ResponseEntity<RideDto> acceptRide(@PathVariable Long rideRequestId){
        return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
    }

    @PostMapping("/startRide")
    public ResponseEntity<RideDto> startRide(@RequestBody StartRideDto startRideDto){
        return ResponseEntity.ok(driverService.startRide(startRideDto.getRideId(), startRideDto.getOtp()));
    }

    @PostMapping("/cancelRide/{rideId}")
    public ResponseEntity<RideDto> cancelRide(@PathVariable Long rideId){
        return ResponseEntity.ok(driverService.cancelRide(rideId));
    }

    @PostMapping("/endRide/{rideId}")
    public ResponseEntity<RideDto> endRide(@PathVariable Long rideId){
        return ResponseEntity.ok(driverService.endRide(rideId));
    }

    @PostMapping("/rateRider")
    public ResponseEntity<RatingDto> endRide(@RequestBody RatingDto ratingDto){
        return ResponseEntity.ok(driverService.rateRider(ratingDto));
    }


}
