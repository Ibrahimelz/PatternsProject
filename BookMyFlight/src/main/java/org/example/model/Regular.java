package org.example.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Regular extends Passenger{
    public Regular(String firstName, String lastName, String phoneNumber, String email, String password) {
        super(firstName, lastName, phoneNumber, email, password);
    }

    public static void wifiLimit() {
        System.out.println("Limited amount of Wifi!");
    }
}
