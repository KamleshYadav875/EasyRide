package com.easyride.easyRideApp.services;

import com.easyride.easyRideApp.dto.DriverDto;
import com.easyride.easyRideApp.dto.LoginResponseDto;
import com.easyride.easyRideApp.dto.SignupDto;
import com.easyride.easyRideApp.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    LoginResponseDto login(String email, String password);

    UserDto signup(SignupDto signupDto);

    DriverDto onBoardNewDriver(Long userId, String vechileid);

    ResponseEntity<LoginResponseDto> refreshToken(String refreshToken);
}
