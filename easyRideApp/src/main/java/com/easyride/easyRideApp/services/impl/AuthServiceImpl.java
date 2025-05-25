package com.easyride.easyRideApp.services.impl;

import com.easyride.easyRideApp.dto.DriverDto;
import com.easyride.easyRideApp.dto.SignupDto;
import com.easyride.easyRideApp.services.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    public void signup(SignupDto signupDto) {

    }

    @Override
    public DriverDto onBoardNewDriver(Long userId) {
        return null;
    }
}
