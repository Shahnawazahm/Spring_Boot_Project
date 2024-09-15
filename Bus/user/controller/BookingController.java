package com.redbus.user.controller;

import com.itextpdf.text.DocumentException;
import com.redbus.user.payload.BookingDto;
import com.redbus.user.payload.PassengerDetails;
import com.redbus.user.service.BookingService;
import com.redbus.util.EmailService;
import com.redbus.util.PdfService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private BookingService bookingService;
    private EmailService emailService;

    public BookingController(BookingService bookingService, EmailService emailService) {
        this.bookingService = bookingService;
        this.emailService = emailService;
    }

    //http://localhost:8080/api/bookings?busId=
   /* @PostMapping
    public ResponseEntity<BookingDto> bookBus(
            @RequestParam("busId") String busId,
            @RequestBody PassengerDetails passengerDetails
    ) {
        BookingDto booking = bookingService.createBooking(busId, passengerDetails);

        if (booking != null) {
            // Generate PDF content
            byte[] pdfContent = generatePdfContent(booking);

            // Send email with PDF attachment
            emailService.sendEmailWithAttachment(
                    passengerDetails.getEmail(),
                    "Booking Confirmed \n Booking Id:" + booking.getBookingId(),
                    "Your booking is confirmed \n Name:" + passengerDetails.getFirstName() + " " + passengerDetails.getLastName(),
                    pdfContent,
                    "BookingDetails.pdf"
            );
        }
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

    private byte[] generatePdfContent(BookingDto bookingDto) {

        try {
            return PdfService.generatePdf(bookingDto);
        } catch (DocumentException e) {
            e.printStackTrace();
            // Handle the exception (log it or throw a custom exception)
            return new byte[0];
        }
    }*/
    @PostMapping
    public ResponseEntity<BookingDto> bookBus(
            @RequestParam("busId") String busId,
            @RequestBody PassengerDetails passengerDetails
    ) {
        BookingDto booking = bookingService.createBooking(busId, passengerDetails);
        if (booking != null) {

            try {
                // Generate PDF content
                byte[] pdfContent = PdfService.generatePdf(booking);

                // Send email with PDF attachment
                emailService.sendBookingConfirmationEmail(
                        passengerDetails.getEmail(),
                        booking.getBookingId(),
                        passengerDetails.getFirstName() + " " + passengerDetails.getLastName(),
                        pdfContent,
                        "BookingDetails.pdf"
                );
            } catch (DocumentException | IOException e) {
                // Handle exceptions appropriately
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/bookings
    @GetMapping
    public ResponseEntity<List<BookingDto>> getAllBooking(){
        List<BookingDto> allBooking = bookingService.getAllBooking();
        return new ResponseEntity<>(allBooking,HttpStatus.OK);
    }

    //http://localhost:8080/api/bookings?bookinId=
    @PutMapping
    public BookingDto updateBookingDetails(
            @RequestParam("bookingId") String bookingId,
          @Valid  @RequestBody BookingDto bookingDto
    ){
        BookingDto bookingDto1 = bookingService.updateBooking(bookingId, bookingDto);
        return bookingDto1;
    }

    @GetMapping("/seatInfo/{busId}")
    public ResponseEntity<String> getSeatAvailabilityInfo(@PathVariable String busId) {
        String seatDetails = bookingService.getSeatAvailabilityInfo(busId);
        return new ResponseEntity<>(seatDetails,HttpStatus.OK);
    }
    @GetMapping("/{busId}/booked-seats")
    public List<Integer> getAllBookedSeats(@PathVariable String busId) {
        return bookingService.getAllBookedSeats(busId);
    }

    @DeleteMapping("/cancel/{bookingId}")
    public String cancelBooking(@PathVariable String bookingId){
        bookingService.cancelBooking(bookingId);
        return "Booking Cancelled";
        }
}


