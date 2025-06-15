package com.easyride.easyRideApp.controllers;

import com.easyride.easyRideApp.dto.DriverDto;
import com.easyride.easyRideApp.dto.OnBoardDriverDto;
import com.easyride.easyRideApp.dto.SignupDto;
import com.easyride.easyRideApp.dto.UserDto;
import com.easyride.easyRideApp.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignupDto signupDto){
        return  new ResponseEntity<>(authService.signup(signupDto), HttpStatus.CREATED);
    }

    @PostMapping("/onBoardNewDriver/{userId}")
    public ResponseEntity<DriverDto> onBoardNewDriver(@RequestBody OnBoardDriverDto onBoardDriverDto){
        return new ResponseEntity<>(authService.onBoardNewDriver(onBoardDriverDto.getUserId(), onBoardDriverDto.getVechileId()), HttpStatus.CREATED);
    }
}
