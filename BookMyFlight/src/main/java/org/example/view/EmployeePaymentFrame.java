package org.example.view;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class EmployeePaymentFrame extends JFrame {
    private EmployeePayment employeePayment;
    @Setter
    @Getter
    private String language = "en";
    private Locale currentLocale;
    private ResourceBundle resourceBundle;

    public EmployeePaymentFrame() throws HeadlessException {
        setTitle("Emplyee PaymentPage");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        employeePayment = new EmployeePayment();
        add(employeePayment.getEmployeePaymentPanel());
        setVisible(true);
    }

    /**
     * Applies i18 to the application by changing the language with english and French
     */
    public void changeLanguage() {
        currentLocale = Locale.of(language, "CA");
        resourceBundle = ResourceBundle.getBundle("messages.message", currentLocale);
        employeePayment.getTicketIdLabel().setText(resourceBundle.getString("ticketId"));
        employeePayment.getTripTypeLabel().setText(resourceBundle.getString("tripType"));
        employeePayment.getOutboundDateLabel().setText(resourceBundle.getString("outboundDate"));
        employeePayment.getReturnDateLabel().setText(resourceBundle.getString("returnDate"));
        employeePayment.getAirplaneLabel().setText(resourceBundle.getString("airplane"));
        employeePayment.getPriceLabel().setText(resourceBundle.getString("price"));
        employeePayment.getPassengerEmailLabel().setText(resourceBundle.getString("passengerEmail"));
        employeePayment.getSelectedTicketsLabel().setText(resourceBundle.getString("selectTicket"));
        employeePayment.getCheckoutButton().setText(resourceBundle.getString("checkout"));
        employeePayment.getExitButton().setText(resourceBundle.getString("returnTicketPage"));
        employeePayment.getStatusLabel().setText(resourceBundle.getString("status"));
        employeePayment.getDestinationLabel().setText(resourceBundle.getString("destination"));
        employeePayment.getDepartureLabel().setText(resourceBundle.getString("departure"));
    }

    public EmployeePayment getEmployeePayment() {
        return employeePayment;
    }
}
