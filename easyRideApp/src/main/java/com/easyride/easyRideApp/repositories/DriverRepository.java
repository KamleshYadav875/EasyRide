package com.easyride.easyRideApp.repositories;

import com.easyride.easyRideApp.entities.Driver;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    @Query(value = "SELECT d.*, ST_DISTANCE(d.current_location, :pickupLocation) AS distance " +
            "from driver d " +
            "where d.available = true AND ST_DWithin(d.current_location, :pickupLocation, 10000 " +
            "order by distance " +
            "LIMIT 10", nativeQuery = true)
    List<Driver> findTENNearestDriver(Point pickupLocation);


    @Query(value = "SELECT d.* " +
            "FROM driver d " +
            "WHERE d.available = true AND ST_DWithin(d.current_location, :pickupLocation, 10000) " +
            "ORDER BY d.rating " +
            "LIMIT 10", nativeQuery = true)
    List<Driver> findTENNearByDriverByRating(Point pickupLocation);
}
