package com.redbus.operator.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.redbus.operator.entity.TicketCost;
import com.redbus.operator.util.CustomDateDeserializer;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusOperatorDto {
    private String busId;
    private String busNumber;
    private String driverName;
    private String supportStaff;
    private String busType;
    private int numberOfSeats;
    private String departureCity;
    private String arrivalCity;

    //@JsonFormat(pattern = "HH:mm:ss")
    private LocalTime departureTime;
    //@JsonFormat(pattern = "HH:mm:ss")
    private LocalTime arrivalTime;

    @JsonFormat(pattern = "dd/MM/yyyy")
   // @JsonSerialize(using=CustomDateDeserializer.class)
    private LocalDate arrivalDate;

     @JsonFormat(pattern = "dd/MM/yyyy")
    //@JsonSerialize(using = CustomDateDeserializer.class)
    private LocalDate departureDate;
    private String amenities;
    private String busOperatorCompanyName;
    private double totalTravelTime;

    private TicketCost ticketCost;
}
