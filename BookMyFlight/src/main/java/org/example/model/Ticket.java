package org.example.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Ticket {
    private int ticketID;
    private int passengerID;
    private Airplane airplane;
    private String outboundDate;
    private String returnDate;
    private double price;
    private String tripType;
    private String status;
    private SeatClass seatType;
    private String departure;
    private String destination;

}
