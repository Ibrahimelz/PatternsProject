package org.example.view;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

@Getter
public class BuyTicketFrame extends JFrame {
    private BuyTicket buyTicket;
    @Setter
    @Getter
    private String language = "en";
    private Locale currentLocale;
    private ResourceBundle resourceBundle;

    public BuyTicketFrame() throws HeadlessException {
        setTitle("Buy Ticket Page");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        buyTicket = new BuyTicket();
        add(buyTicket.getBuyTicketPanel());
        setVisible(true);
    }

    /**
     * Applies i18 to the application by changing the language with english and French
     */
    public void changeLanguage() {
        currentLocale = Locale.of(language, "CA");
        resourceBundle = ResourceBundle.getBundle("messages.message", currentLocale);
        buyTicket.getCardNumberLabel().setText(resourceBundle.getString("cardNumber"));
        buyTicket.getCardHolderNameLabel().setText(resourceBundle.getString("cardHolder"));
        buyTicket.getCardExpirationDateLabel().setText(resourceBundle.getString("expirationDate"));
        buyTicket.getCvcLabel().setText(resourceBundle.getString("cvc"));
        buyTicket.getIsEmployeeCheckBox().setText(resourceBundle.getString("isEmployee"));
        buyTicket.getPayTicketsButton().setText(resourceBundle.getString("payTickets"));
    }
}
