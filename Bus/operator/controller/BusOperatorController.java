package com.redbus.operator.controller;

import com.redbus.operator.payload.BusOperatorDto;
import com.redbus.operator.service.BusOperatorService;
import com.redbus.user.payload.BookingDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/bus-operator")
public class BusOperatorController {

    private BusOperatorService busOperatorService;
    public BusOperatorController(BusOperatorService busOperatorService){
        this.busOperatorService=busOperatorService;
    }

    //http://localhost:8080/api/bus-operator
    @PostMapping
    public ResponseEntity<?> scheduleBus(
            @Valid @RequestBody BusOperatorDto busOperatorDto,
            BindingResult result){
        if(result.hasErrors()) {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
            BusOperatorDto dto = busOperatorService.scheduleBus(busOperatorDto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }

    //http://localhost:8080/api/bus-operator?pageNo=0&pageSize&sortBy&sortDir
    @GetMapping
    public List<BusOperatorDto> getAllBusOperators(
            @RequestParam(name="pageNo",required = false,defaultValue = "0")int pageNo,
            @RequestParam(name="pageSize",required = false,defaultValue = "5")int pageSize,
            @RequestParam(value="sortBy",required = false,defaultValue ="busId") String sortBy,
            @RequestParam(value="sortDir",defaultValue = "asc",required = false) String sortDir

            ){
       List<BusOperatorDto> dtos= busOperatorService.getAllBusOperators(pageNo,pageSize,sortBy,sortDir);
       return dtos;
    }

    //http://localhost:8080/api/bus-operator?busId=
    @PutMapping
    public ResponseEntity<BusOperatorDto> updateBusDetails(
            @RequestParam String busId,
            @RequestBody BusOperatorDto busOperatorDto
    ){
        BusOperatorDto busOperatorDto1=busOperatorService.updateBusDetails(busId,busOperatorDto);
       return new ResponseEntity<>(busOperatorDto1,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{busId}")
    public ResponseEntity<String> deleteBus(@PathVariable("busId") String busId){
        busOperatorService.deleteBus(busId);
        return new ResponseEntity<>("Bus Deleted with Id:"+busId,HttpStatus.OK);
     }
    @GetMapping("/{busId}")
    public BusOperatorDto getBusById(@PathVariable String busId){
        BusOperatorDto busOperatorDto=busOperatorService.getBusById(busId);
        return busOperatorDto;
    }
}
