package org.example.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public abstract class User {
    private int userID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

/* TODO
    public void bookTicket(Ticket ticket){

    }

    public boolean validateIdentification(){

    }

    public boolean validateUser(){

    }
*/
}
