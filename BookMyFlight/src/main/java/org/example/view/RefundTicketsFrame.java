package org.example.view;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class RefundTicketsFrame extends JFrame {
    private RefundTickets refundTickets;
    @Setter
    @Getter
    private String language = "en";
    private Locale currentLocale;
    private ResourceBundle resourceBundle;

    public RefundTicketsFrame() throws HeadlessException {
        setTitle("Refund Page");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        refundTickets = new RefundTickets();
        add(refundTickets.getRefundTicketsPanel());
        setVisible(true);
    }

    /**
     * Applies i18 to the application by changing the language with english and French
     */
    public void changeLanguage() {
        currentLocale = Locale.of(language, "CA");
        resourceBundle = ResourceBundle.getBundle("messages.message", currentLocale);
        refundTickets.getInstructionsLabel().setText(resourceBundle.getString("employeeRefund"));
        refundTickets.getTicketCancelLabel().setText(resourceBundle.getString("nextCanceled"));
        refundTickets.getRefundTicketsButton().setText(resourceBundle.getString("refund"));
        refundTickets.getLogoutButton().setText(resourceBundle.getString("logout"));
    }

    public RefundTickets getRefundTickets() {
        return refundTickets;
    }
}
