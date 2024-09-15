package com.redbus.user.controller;

import com.redbus.operator.entity.BusOperator;
import com.redbus.user.payload.BusListDto;
import com.redbus.user.service.SearchBusesService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class FindBusesController {

    private SearchBusesService searchBusesService;

    public FindBusesController(SearchBusesService searchBusesService) {
        this.searchBusesService = searchBusesService;
    }

    //http://localhost:8080/api/user/searchBuses?departureCity=&arrivalCity=&departureDate=
    @GetMapping("/searchBuses")
    public List<BusListDto> searchBuses(
            @RequestParam("departureCity") String departureCity,
            @RequestParam("arrivalCity") String arrivalCity,
            @RequestParam("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate departureDate){
        List<BusListDto> busListDtos = searchBusesService.searchBusBy(departureCity, arrivalCity, departureDate);
        return busListDtos;
    }
}
