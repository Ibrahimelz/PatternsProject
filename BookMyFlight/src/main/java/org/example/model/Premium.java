package org.example.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Premium extends Passenger {
    private static final int bonusDiscount = 500;

    public Premium(String passengerID, String firstName, String lastName, String phoneNumber, String email, int cardNumber, String cardHolderName, String cardExpirationDate, int cvc) {
        super(passengerID, firstName, lastName, phoneNumber, email, cardNumber, cardHolderName, cardExpirationDate, cvc);
        this.setCredits(this.getCredits()+bonusDiscount);
    }
}
