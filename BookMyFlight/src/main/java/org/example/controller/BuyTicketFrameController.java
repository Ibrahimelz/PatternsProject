package org.example.controller;

import org.example.model.Employee;
import org.example.model.Passenger;
import org.example.model.Ticket;
import org.example.view.BuyTicketFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BuyTicketFrameController {
    private BuyTicketFrame buyTicketFrame;
    private UserController userControllercontroller;

    public BuyTicketFrameController(List<Ticket> tickets, Passenger passenger, boolean isAnEmployee) {
        System.out.println(passenger.getTicketsList());
        this.userControllercontroller = new UserController();
        buyTicketFrame = new BuyTicketFrame();
        buyTicketFrame.getBuyTicket().getIsEmployeeCheckBox().setEnabled(false);
        buyTicketFrame.getBuyTicket().getPayTicketsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cardNum = buyTicketFrame.getBuyTicket().getCardNumberTextField().getText();
                String cardName = buyTicketFrame.getBuyTicket().getCardHolderNameTextField().getText();
                String expiration = buyTicketFrame.getBuyTicket().getExpirationDateTextField().getText();
                String cvc = buyTicketFrame.getBuyTicket().getCvcTextField().getText();
                boolean allValidated = Passenger.validatePaymentInput(cardNum, cardName, expiration, cvc);
                if (!allValidated) {
                    buyTicketFrame.getBuyTicket().getErrorMessageLabel().setText("Please make sure to have a valid input!");
                } else {
                    AirLineTicketSystemController.updatePassengerCardInfo(passenger, cardName, cardNum, expiration, Integer.parseInt(cvc));
                    userControllercontroller.bookTickets(passenger, tickets);
                    for (Ticket ticket : tickets) {
                        userControllercontroller.removeSeatFromAirplane(ticket);
                    }
                    userControllercontroller.payForTickets(passenger, tickets);
                    DatabaseController.updatePassengerCreditRecord(passenger);
                    DatabaseController.updatePassengerRecord(passenger);
                    if (isAnEmployee) {
                        buyTicketFrame.getBuyTicket().getIsEmployeeCheckBox().setSelected(true);
                        new RefundTicketsFrameController(passenger);
                    } else {
                        buyTicketFrame.getBuyTicket().getIsEmployeeCheckBox().setSelected(false);
                        new ExitFrameController(tickets, passenger);
                    }
                    buyTicketFrame.setVisible(false);
                }
            }
        });
        buyTicketFrame.getBuyTicket().getLanguageButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buyTicketFrame.getBuyTicket().getLanguageButton().getText().equals("en")) {
                    buyTicketFrame.setLanguage("en");
                    buyTicketFrame.changeLanguage();
                    buyTicketFrame.getBuyTicket().getLanguageButton().setText("fr");
                } else {
                    buyTicketFrame.setLanguage("fr");
                    buyTicketFrame.changeLanguage();
                    buyTicketFrame.getBuyTicket().getLanguageButton().setText("en");
                }
            }
        });
    }
}
