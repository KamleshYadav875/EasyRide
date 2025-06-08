package com.easyride.easyRideApp.strategies;

import com.easyride.easyRideApp.strategies.impl.DriverMatchingHigestRatedDriverStrategy;
import com.easyride.easyRideApp.strategies.impl.DriverMatchingNearestDriverStrategy;
import com.easyride.easyRideApp.strategies.impl.RideFareDefaultFareCalculationStrategy;
import com.easyride.easyRideApp.strategies.impl.RideFareSurgePricingFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {

    private final DriverMatchingNearestDriverStrategy nearestDriverStrategy;
    private final DriverMatchingHigestRatedDriverStrategy higestRatedDriverStrategy;
    private final RideFareDefaultFareCalculationStrategy defaultFareCalculationStrategy;
    private final RideFareSurgePricingFareCalculationStrategy surgePricingFareCalculationStrategy;

    public DriverMatchingStrategy driverMatchingStrategy(double rating){
        if(rating > 4.5){
            return higestRatedDriverStrategy;
        }
        else {
            return nearestDriverStrategy;
        }
    }

    public RideFareCalculationStrategy rideFareCalculationStrategy(){
        LocalTime surgeStartTime = LocalTime.of(18,0 );
        LocalTime surgeEndTime = LocalTime.of(21,0 );
        LocalTime currentTime = LocalTime.now();

        if(currentTime.isBefore(surgeEndTime) && currentTime.isAfter(surgeStartTime)){
            return surgePricingFareCalculationStrategy;
        }
        else {
            return defaultFareCalculationStrategy;
        }

    }
}
