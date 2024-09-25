package com.redbus.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.redbus.user.payload.BookingDto;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService {
    public static byte[] generatePdf(BookingDto bookingDto) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);

        document.open();

        // Add content to the PDF
        document.add(new Paragraph("Booking ID: " + bookingDto.getBookingId()));
        document.add(new Paragraph("Ticket ID: " + bookingDto.getTicketId()));
        document.add(new Paragraph("Bus Company: " + bookingDto.getBusCompany()));
        document.add(new Paragraph("Bus Type: " + bookingDto.getBusType()));
        document.add(new Paragraph("From City: " + bookingDto.getFromCity()));
        document.add(new Paragraph("To City: " + bookingDto.getToCity()));
        document.add(new Paragraph("Departure Time: " + bookingDto.getDepartureTime()));
        document.add(new Paragraph("Arrival Time: " + bookingDto.getArrivalTime()));
        document.add(new Paragraph("Arrival Date: " + bookingDto.getArrivalDate()));
        document.add(new Paragraph("Departure Date: " + bookingDto.getDepartureDate()));
        document.add(new Paragraph("Total Travel Time: " + bookingDto.getTotalTravelTime()));
        document.add(new Paragraph("Amenities: " + bookingDto.getAmenities()));
        document.add(new Paragraph("Number of Seats: " + bookingDto.getNumberOfSeats()));
        document.add(new Paragraph("First Name: " + bookingDto.getFirstName()));
        document.add(new Paragraph("Last Name: " + bookingDto.getLastName()));
        document.add(new Paragraph("Email: " + bookingDto.getEmail()));
        document.add(new Paragraph("Mobile: " + bookingDto.getMobile()));
        document.add(new Paragraph("Price: " + bookingDto.getPrice()));
        document.add(new Paragraph("Seat Number:"+bookingDto.getSeatNumber()));

        document.close();

        return baos.toByteArray();
    }
}
