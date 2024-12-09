package org.example.model;

import lombok.*;

@EqualsAndHashCode
public class Regular extends Passenger {

    public Regular(String firstName, String lastName, String phoneNumber, String email, String password) {
        super(firstName, lastName, phoneNumber, email, password);
    }

    public Regular() {
        super();
    }

    /**
     * Returns that the regular user has a limited wifi
     * @return the string message
     */
    public String wifiLimit() {
        return "Limited Wifi!";
    }

    @Override
    public String toString() {
        return "Regular{" +
                "credits=" + credits +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", cardExpirationDate='" + cardExpirationDate + '\'' +
                ", cvc=" + cvc +
                ", ticketsList=" + ticketsList +
                ", refundList=" + refundList +
                ", userID='" + userID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
