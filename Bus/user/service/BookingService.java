package com.redbus.user.service;

import com.redbus.user.payload.BookingDto;
import com.redbus.user.payload.PassengerDetails;

import java.util.List;

public interface BookingService {
    BookingDto createBooking(String busId, PassengerDetails passengerDetails);

    List<BookingDto> getAllBooking();

    BookingDto updateBooking(String bookingId, BookingDto bookingDto);

    String getSeatAvailabilityInfo(String busId);

    List<Integer> getAllBookedSeats(String busId);

    void cancelBooking(String bookingId);
}
