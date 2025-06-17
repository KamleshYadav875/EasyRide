package com.easyride.easyRideApp.controllers;

import com.easyride.easyRideApp.dto.*;
import com.easyride.easyRideApp.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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

    @PostMapping("/onBoardNewDriver")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<DriverDto> onBoardNewDriver(@RequestBody OnBoardDriverDto onBoardDriverDto){
        return new ResponseEntity<>(authService.onBoardNewDriver(onBoardDriverDto.getUserId(), onBoardDriverDto.getVechileId()), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
       String token[]  = authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        Cookie cookie = new Cookie("token", token[0]);
        cookie.setSecure(false);
        cookie.setHttpOnly(true);

        httpServletResponse.addCookie(cookie);
       return ResponseEntity.ok(new LoginResponseDto(token[0]));
    }
}
