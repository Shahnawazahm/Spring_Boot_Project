package com.redbus.operator.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.redbus.operator.util.CustomDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "bus_operator")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusOperator {
    @Id
    private String busId;
    @NotBlank(message = "Bus number is required")
    @Size(max = 20, message = "Bus number must be at most 20 characters")
    @Column(name="bus_number")
    private String busNumber;

    @NotBlank(message = "Driver name cannot be blank")
    @Size(max = 50, message = "Driver name must be at most 50 characters")
    @Column(name="driver_name")
    private String driverName;

    @NotBlank(message = "support staff name cannot be blank")
    @Size(max = 50, message = "support staff name must be at most 50 characters")
    @Column(name="support_staff")
    private String supportStaff;

    @NotBlank(message = "busType name cannot be blank")
    @Column(name="bus_type")
    private String busType;

    @Min(value =0)
    @Column(name="number_of_seats")
    private int numberOfSeats;

    @NotBlank(message = "Departure city cannot be blank")
    @Column(name="departure_city")
    private String departureCity;

    @NotBlank(message = "Arrival city cannot be blank")
    @Column(name="arrival_city")
    private String arrivalCity;

    @NotNull(message = "Departure time cannot be null")
    @Column(name="departure_time")
    private LocalTime departureTime;

    @NotNull(message = "Arrival time cannot be null")
    @Column(name="arrival_time")
    private LocalTime arrivalTime;

    @NotNull(message = "Arrival date cannot be null")
    @Column(name="arrival_date")
    private LocalDate arrivalDate;

    @NotNull(message = "Departure date cannot be null")
    @Column(name="departure_date")
    private LocalDate departureDate;

    @NotBlank(message = "Amenities cannot be blank")
    @Column(name="amenities")
    private String amenities;

    @NotBlank(message = "Company name cannot be blank")
    @Size(max = 100, message = "Company name must be at most 100 characters")
    @Column(name="bus_operator_company_name")
    private String busOperatorCompanyName;

    @Column(name="total_travel_time")
    @Min(value = 0, message = "Total travel time must be a non-negative value")
    private double totalTravelTime;

    @OneToOne(mappedBy = "busOperator", cascade = CascadeType.ALL, orphanRemoval = true)
    private TicketCost ticketCost;
}
