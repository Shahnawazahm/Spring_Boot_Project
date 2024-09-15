package com.redbus.user.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusListDto {
    private String busId;
    private String busNumber;
    private String busType;
    private int numberOfSeats;
    private String departureCity;
    private String arrivalCity;

    //@JsonFormat(pattern = "HH:mm:ss")
    private LocalTime departureTime;
    //@JsonFormat(pattern = "HH:mm:ss")
    private LocalTime arrivalTime;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate arrivalDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate departureDate;
    private String amenities;
    private String busOperatorCompanyName;
    private double totalTravelTime;
}
