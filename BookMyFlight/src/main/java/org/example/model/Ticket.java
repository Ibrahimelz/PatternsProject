package org.example.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Ticket {
    private String ticketID;
    private int passengerID;
    private Airplane airplane;
    private String outboundDate;
    private String returnDate;
    private double price;
    private String tripType;
    private TicketStatus status;
    private SeatClass seatType;
    private String departure;
    private String destination;

}
