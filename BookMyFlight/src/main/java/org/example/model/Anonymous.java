package org.example.model;

public class Anonymous extends Passenger {
    public Anonymous(String passengerID, String firstName, String lastName, String phoneNumber, String email, int cardNumber, String cardHolderName, String cardExpirationDate, int cvc) {
        super(passengerID, firstName, lastName, phoneNumber, email, cardNumber, cardHolderName, cardExpirationDate, cvc);
    }
    /* TODO
    public void applyAnonymousCredentials(){

    }
    */
}
