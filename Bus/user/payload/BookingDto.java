package com.redbus.user.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    //these are the details that i m giving in json.we will give the busId and ticketId in url
    private String bookingId;
    private String ticketId;
    private String busCompany;
    private String busType;
    private String toCity;
    private String fromCity;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate arrivalDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate departureDate;
    private double totalTravelTime;
    private String amenities;
    private int seatNumber;
    private int numberOfSeats;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private double price;
    private String clientSecret;

}
