package com.redbus.user.repository;

import com.redbus.user.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,String> {
    List<Booking> findByBusId(String busId);

    Booking findBySeatNumber(int seatNumber);
}
