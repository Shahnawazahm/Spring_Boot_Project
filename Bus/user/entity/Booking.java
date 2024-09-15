package com.redbus.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
@Entity
@Table(name="bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    private String bookingId;

    @Column(name = "bus_Id")
    private String busId;

    @Column(name="ticket_Id")
    private String ticketId;

    @Column(name="bus_Company")
    private String busCompany;

    @Column(name="bus_type")
    private String busType;

    @Column(name="to_city")
    private String toCity;

    @Column(name="from_city")
    private String fromCity;

    @Column(name="departure_time")
    private LocalTime departureTime;

    @Column(name="arrival_time")
    private LocalTime arrivalTime;

    @Column(name="arrival_date")
    private LocalDate arrivalDate;

    @Column(name="departure_date")
    private LocalDate departureDate;

    @Column(name="total_travel_time")
    private double totalTravelTime;

    @Column(name="amenities")
    private String amenities;

    @Column(name="seat_number")
    private int seatNumber;

    @Column(name="number_of_seats")
    private int numberOfSeats;
    //private int seaNumber;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="mobile")
    private String mobile;

    @Column(name="price")
    private double price;


}
