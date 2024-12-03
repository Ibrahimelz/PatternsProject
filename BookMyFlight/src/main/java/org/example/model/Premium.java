package org.example.model;

import lombok.*;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Premium extends Passenger {
    private static final int BONUS_CREDIT = 500;

    public Premium(String passengerID, String firstName, String lastName, String phoneNumber, String email,
                   int cardNumber, String cardHolderName, String cardExpirationDate, int cvc) {
        super(passengerID, firstName, lastName, phoneNumber, email);
        this.setCredits(this.getCredits() + BONUS_CREDIT);
    }
}
