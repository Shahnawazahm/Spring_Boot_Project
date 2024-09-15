package com.redbus.user.service;

import com.redbus.operator.entity.BusOperator;
import com.redbus.user.payload.BusListDto;

import java.time.LocalDate;
import java.util.List;

public interface SearchBusesService {
    List<BusListDto> searchBusBy(String departureCity, String arrivalCity, LocalDate departureDate);
}

