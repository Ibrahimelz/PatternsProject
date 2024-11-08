package org.example.model;

import lombok.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Passenger extends User{
    private int credits;
    private String cardExpirationDate;
    private int cvc;
    private String cardHolderName;
    private int cardNumber;
    private List<Ticket> ticketsList = new LinkedList<>();
    private List<CancelOperation> refundList = new LinkedList<>();

    /* TODO
    public double calculateNewTicketPrice(){

    }

    public void payTicket(Ticket ticket){

    }

    public void deductCredit(double discount){

    }

    public boolean validatePayment(){

    }

    public void requestRefund(int ticketID){

    }

    public double calculateDiscountWithCredit(){

    }
    */
}
