package com.easyride.easyRideApp.services;

import com.easyride.easyRideApp.dto.DriverDto;
import com.easyride.easyRideApp.dto.SignupDto;

public interface AuthService {

    String login(String email, String password);

    void signup(SignupDto signupDto);

    DriverDto onBoardNewDriver(Long userId);
}
