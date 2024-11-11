package org.example.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Regular extends Passenger{
    private int numOfBagages;

    public Regular(String passengerID, String firstName, String lastName, String phoneNumber, String email, int cardNumber, String cardHolderName, String cardExpirationDate, int cvc, int numOfBagages) {
        super(passengerID, firstName, lastName, phoneNumber, email, cardNumber, cardHolderName, cardExpirationDate, cvc);
        this.numOfBagages = numOfBagages;
    }
}
