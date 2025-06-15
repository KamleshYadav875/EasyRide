package com.easyride.easyRideApp.services;

import com.easyride.easyRideApp.dto.DriverDto;
import com.easyride.easyRideApp.dto.SignupDto;
import com.easyride.easyRideApp.dto.UserDto;

public interface AuthService {

    String login(String email, String password);

    UserDto signup(SignupDto signupDto);

    DriverDto onBoardNewDriver(Long userId, String vechileid);
}
