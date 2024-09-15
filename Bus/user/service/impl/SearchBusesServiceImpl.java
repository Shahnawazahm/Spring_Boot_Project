package com.redbus.user.service.impl;

import com.redbus.operator.entity.BusOperator;
import com.redbus.operator.repository.BusOperatorRepository;
import com.redbus.user.payload.BusListDto;
import com.redbus.user.service.SearchBusesService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchBusesServiceImpl implements SearchBusesService {
    private BusOperatorRepository busOperatorRepository;

    public SearchBusesServiceImpl(BusOperatorRepository busOperatorRepository) {
        this.busOperatorRepository = busOperatorRepository;
    }

    @Override
    public List<BusListDto> searchBusBy(String departureCity, String arrivalCity, LocalDate departureDate) {
        List<BusOperator> busesAvaliable = busOperatorRepository.findByDepartureCityAndArrivalCityAndDepartureDate(departureCity, arrivalCity, departureDate);
        List<BusListDto> dtos = busesAvaliable.stream().map(bus -> mapToDto(bus)).collect(Collectors.toList());
        return dtos;

}

    private BusListDto mapToDto(BusOperator busOperator) {
        BusListDto busListDto = new BusListDto();
        busListDto.setBusId(busOperator.getBusId());
        busListDto.setBusNumber(busOperator.getBusNumber());
        busListDto.setBusType(busOperator.getBusType());
        busListDto.setAmenities(busOperator.getAmenities());
        busListDto.setDepartureCity(busOperator.getDepartureCity());
        busListDto.setArrivalCity(busOperator.getArrivalCity());
        busListDto.setDepartureDate(busOperator.getDepartureDate());
        busListDto.setArrivalDate(busOperator.getArrivalDate());
        busListDto.setDepartureTime(busOperator.getDepartureTime());
        busListDto.setArrivalTime(busOperator.getArrivalTime());
        busListDto.setBusOperatorCompanyName(busOperator.getBusOperatorCompanyName());
        busListDto.setNumberOfSeats(busOperator.getNumberOfSeats());
        busListDto.setTotalTravelTime(busOperator.getTotalTravelTime());
        return busListDto;
    }
    }
