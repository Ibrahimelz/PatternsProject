package org.example.view;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class PaymentFrame extends JFrame {
    private Payment payment;
    @Setter
    @Getter
    private String language = "en";
    private Locale currentLocale;
    private ResourceBundle resourceBundle;

    public PaymentFrame() throws HeadlessException {
        setTitle("Payment Page");
        setSize(620, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        payment = new Payment();
        add(payment.getPaymentPanel());
        setVisible(true);
    }

    /**
     * Applies i18 to the application by changing the language with english and French
     */
    public void changeLanguage() {
        currentLocale = Locale.of(language, "CA");
        resourceBundle = ResourceBundle.getBundle("messages.message", currentLocale);
        payment.getTicketIdLabel().setText(resourceBundle.getString("ticketId"));
        payment.getTripTypeLabel().setText(resourceBundle.getString("tripType"));
        payment.getOutboundDateLabel().setText(resourceBundle.getString("outboundDate"));
        payment.getReturnDateLabel().setText(resourceBundle.getString("returnDate"));
        payment.getAirplaneLabel().setText(resourceBundle.getString("airplane"));
        payment.getPriceLabel().setText(resourceBundle.getString("price"));
        payment.getPickTicketsLabel().setText(resourceBundle.getString("selectTickets"));
        payment.getCheckoutButton().setText(resourceBundle.getString("checkout"));
        payment.getExitButton().setText(resourceBundle.getString("returnTicketPage"));
        payment.getStatusLabel().setText(resourceBundle.getString("status"));
        payment.getDestinationLabel1().setText(resourceBundle.getString("destination"));
        payment.getDepartureLabel1().setText(resourceBundle.getString("departure"));
        payment.getCreditAmountLabel().setText(resourceBundle.getString("credits"));
    }

    public Payment getPayment() {
        return payment;
    }
}
