package com.easyride.easyRideApp.services.impl;

import com.easyride.easyRideApp.dto.DriverDto;
import com.easyride.easyRideApp.dto.LoginResponseDto;
import com.easyride.easyRideApp.dto.SignupDto;
import com.easyride.easyRideApp.dto.UserDto;
import com.easyride.easyRideApp.entities.Driver;
import com.easyride.easyRideApp.entities.User;
import com.easyride.easyRideApp.entities.enums.Role;
import com.easyride.easyRideApp.exceptions.ResourceNotFoundException;
import com.easyride.easyRideApp.exceptions.RunTimeConfilictException;
import com.easyride.easyRideApp.repositories.UserRepository;
import com.easyride.easyRideApp.security.JWTService;
import com.easyride.easyRideApp.services.AuthService;
import com.easyride.easyRideApp.services.DriverService;
import com.easyride.easyRideApp.services.RiderService;
import com.easyride.easyRideApp.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;

    private  final UserRepository userRepository;

    private final RiderService riderService;

    private final WalletService walletService;

    private final DriverService driverService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    @Override
    public LoginResponseDto login(String email, String password) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        User user = (User) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        LoginResponseDto loginResponseDto = new LoginResponseDto(accessToken, refreshToken);
        return loginResponseDto;
    }

    @Override
    public UserDto signup(SignupDto signupDto) {

        User userCheck = userRepository.findByEmail(signupDto.getEmail()).orElse(null);
        if(userCheck != null){
            throw  new RunTimeConfilictException("Cannot signup, User already exists with email "+signupDto.getEmail());
        }

        User user = modelMapper.map(signupDto, User.class);
        user.setRoles(Set.of(Role.RIDER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        // Create A rider
        riderService.createRider(savedUser);

        // Create Wallet for a user
        walletService.createNewWallet(savedUser);

        return modelMapper.map(user, UserDto.class);

    }


    @Override
    public DriverDto onBoardNewDriver(Long userId, String vehicleId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with user id: "+userId));

        if(user.getRoles().contains(Role.DRIVER)){
            throw new RunTimeConfilictException("User with id "+userId +" is already a driver.");
        }

        Driver driver = Driver.builder()
                .available(true)
                .user(user)
                .rating(0.0)
                .vehicleId(vehicleId)
                .build();

        Driver savedDriver = driverService.createDriver(driver);

        user.getRoles().add(Role.DRIVER);
        userRepository.save(user);

        return modelMapper.map(savedDriver, DriverDto.class);
    }

    @Override
    public ResponseEntity<LoginResponseDto> refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with user id: "+userId));
        String accessToken = jwtService.generateRefreshToken(user);

        LoginResponseDto loginResponseDto = new LoginResponseDto(accessToken, refreshToken);
        return ResponseEntity.ok(loginResponseDto);
    }
}
