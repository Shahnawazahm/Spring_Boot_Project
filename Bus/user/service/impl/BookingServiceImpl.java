package com.redbus.user.service.impl;

import com.redbus.exception.EntityNotFoundException;
import com.redbus.operator.entity.BusOperator;
import com.redbus.operator.repository.BusOperatorRepository;
import com.redbus.user.entity.Booking;
import com.redbus.user.payload.BookingDto;
import com.redbus.user.payload.PassengerDetails;
import com.redbus.user.repository.BookingRepository;
import com.redbus.user.service.BookingService;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.stripe.exception.StripeException;

import java.util.ArrayList;
import java.util.List;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    private ModelMapper mapper;
    private BusOperatorRepository busOperatorRepository;
    private BookingRepository bookingRepository;

    public BookingServiceImpl(BusOperatorRepository busOperatorRepository, BookingRepository bookingRepository, ModelMapper mapper) {
        this.busOperatorRepository = busOperatorRepository;
        this.bookingRepository = bookingRepository;
        this.mapper = mapper;
    }
    @Value("${stripe.api.key.secret}")
    private String stripeSecretKey;
    @Override
    public BookingDto createBooking(
            String busId,
            PassengerDetails passengerDetails) {
        // BusOperator busOperator = busOperatorRepository.findById(busId).get();
        BusOperator busOperator = busOperatorRepository.findById(busId).orElseThrow(
                () -> new EntityNotFoundException("Bus Operator not found")
        );

        int seatNumber = passengerDetails.getSeatNumber();
        Booking bySeatNumber = bookingRepository.findBySeatNumber(seatNumber);

        Booking booking = new Booking();
        if (busOperator.getNumberOfSeats() > 0 ) {
            if(bySeatNumber==null){
                booking.setSeatNumber(seatNumber);
            }else{
                throw new RuntimeException("Seat Number already Present");
            }
            // Decrease the number of available seats
            busOperator.setNumberOfSeats(busOperator.getNumberOfSeats() - 1);
            busOperatorRepository.save(busOperator);

            String bookingId = UUID.randomUUID().toString();
            booking.setBookingId(bookingId);
            booking.setBusId(busOperator.getBusId());
            booking.setToCity(busOperator.getArrivalCity());
            booking.setFromCity(busOperator.getDepartureCity());
            booking.setAmenities(busOperator.getAmenities());
            booking.setArrivalDate(busOperator.getArrivalDate());
            booking.setDepartureDate(busOperator.getDepartureDate());
            booking.setTotalTravelTime(busOperator.getTotalTravelTime());
            booking.setArrivalTime(busOperator.getArrivalTime());
            booking.setDepartureTime(busOperator.getDepartureTime());
            booking.setNumberOfSeats(busOperator.getNumberOfSeats());
            booking.setPrice(busOperator.getTicketCost().getCost());
            booking.setTicketId(busOperator.getTicketCost().getTicketId());
            booking.setBusCompany(busOperator.getBusOperatorCompanyName());
            booking.setBusType(busOperator.getBusType());
            booking.setFirstName(passengerDetails.getFirstName());
            booking.setLastName(passengerDetails.getLastName());
            booking.setEmail(passengerDetails.getEmail());
            booking.setMobile(passengerDetails.getMobile());

            Booking savedBooking = bookingRepository.save(booking);

            // After booking is done, create a PaymentIntent for the amount
            ResponseEntity<String> responseEntity = createPaymentIntent((int) busOperator.getTicketCost().getCost());
            String clientSecret = responseEntity.getBody();

            if (responseEntity.getStatusCode().is2xxSuccessful() && clientSecret != null) {
                BookingDto bookingDto = mapToDto(savedBooking);
                bookingDto.setClientSecret(clientSecret);
                return bookingDto;
            } else {
                // Handle payment creation failure
                throw new EntityNotFoundException("Error creating PaymentIntent");
            }
        } else {
            throw new RuntimeException("No available seats for booking");
        }
    }


    @Override
    public List<BookingDto> getAllBooking() {
        List<Booking> bookingList = bookingRepository.findAll();
        List<BookingDto> bookingDtoList = bookingList.stream().map(list -> mapToDto(list)).collect(Collectors.toList());
        return bookingDtoList;
    }

    @Override
    public BookingDto updateBooking(String bookingId, BookingDto bookingDto) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(
                () -> new EntityNotFoundException("Booking not found with Id" + bookingId)
        );
        booking.setFirstName(bookingDto.getFirstName());
        booking.setLastName(bookingDto.getLastName());
        booking.setEmail(bookingDto.getEmail());
        booking.setMobile(bookingDto.getMobile());

        BookingDto bookingDto1 = mapToDto(booking);
        return bookingDto1;
    }

    @Override
    public String getSeatAvailabilityInfo(String busId) {
                   BusOperator busOperator = busOperatorRepository.findById(busId)
                    .orElseThrow(() -> new EntityNotFoundException("busId not found " + busId));
            int availableSeats = busOperator.getNumberOfSeats();
            return "Available seats: "+availableSeats;
        }

    @Override
    public List<Integer> getAllBookedSeats(String busId) {
                   List<Booking> bookedSeats = bookingRepository.findByBusId(busId);
            List<Integer> bookedSeatNumbers = new ArrayList<>();

            for (Booking booking : bookedSeats) {
                bookedSeatNumbers.add(booking.getSeatNumber());
            }

            return bookedSeatNumbers;
        }

    @Override
    public void cancelBooking(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(
                () -> new EntityNotFoundException("Booking Not Found with Id:" + bookingId)
        );
        bookingRepository.delete(booking);
    }


    private BookingDto mapToDto(Booking savedBooking) {
        BookingDto bookingDto = mapper.map(savedBooking, BookingDto.class);
        return bookingDto;
    }


    public ResponseEntity<String> createPaymentIntent(Integer amount) {
        // Set your secret key from the Stripe dashboard
        Stripe.apiKey = stripeSecretKey;
        try {


            // Create a PaymentIntent
            PaymentIntentCreateParams params = new PaymentIntentCreateParams.Builder()
                    .setCurrency("usd")
                    .setAmount((long)amount*100) // The amount is in cents, change it based on your requirement
                    .build();

            PaymentIntent paymentIntent = PaymentIntent.create(params);

            // Return the client secret to the frontend
            return ResponseEntity.ok(paymentIntent.getClientSecret());
        } catch (StripeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating PaymentIntent");
        }
    }
}