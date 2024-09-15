package com.redbus.user.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDetails {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Email
    private String email;

    @Digits(integer = 10, fraction = 0, message = "Invalid phone number. It should be exactly 10 digits.")
    private String mobile;

    private int seatNumber;
}
