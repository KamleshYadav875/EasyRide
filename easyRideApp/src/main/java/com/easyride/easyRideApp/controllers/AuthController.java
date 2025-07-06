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
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

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
        LoginResponseDto loginResponseDto  = authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        Cookie cookie = new Cookie("refreshToken", loginResponseDto.getRefreshToken());
        cookie.setSecure(false);
        cookie.setHttpOnly(true);

        httpServletResponse.addCookie(cookie);
        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/refresh")
    ResponseEntity<LoginResponseDto> refresh(HttpServletRequest request){
        Cookie cookies[] = request.getCookies();
        String refreshToken = Arrays.stream( cookies)
                .filter( cookie -> cookie.getName().equals("refreshToken"))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow( () -> new AuthenticationServiceException("Invalid Refresh Token!!"));

       return authService.refreshToken(refreshToken);

    }
}
