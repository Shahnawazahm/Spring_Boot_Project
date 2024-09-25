package com.redbus.util;
import com.sendgrid.helpers.mail.objects.Attachments;
import org.springframework.beans.factory.annotation.Value;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;

@Service
public class EmailService {
    @Value("${spring.sendgrid.api-key}")
    private String sendGridApiKey;


    public void sendBookingConfirmationEmail(String toEmail, String bookingId, String fullName, byte[] pdfAttachment, String attachmentFileName) throws IOException {
        Email from = new Email("adfaramushtaq@gmail.com");
        Email to = new Email(toEmail);
        String subject = "Booking Confirmation - " + bookingId;
        String body = "Your booking is confirmed.\n\nBooking ID: " + bookingId + "\nName: " + fullName;

        Content content = new Content("text/plain", body);
        Mail mail = new Mail(from, subject, to, content);

        // Attach the PDF
        Attachments attachments = new Attachments();
        attachments.setContent(Base64.getEncoder().encodeToString(pdfAttachment));
        attachments.setType("application/pdf");
        attachments.setFilename(attachmentFileName);
        attachments.setDisposition("attachment");
        mail.addAttachments(attachments);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();

        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());

        Response response = sg.api(request);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
        System.out.println(response.getHeaders());
    }
}
