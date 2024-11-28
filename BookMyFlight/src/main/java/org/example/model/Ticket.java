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
    @Getter
    @Setter
    private String passengerID;
    private Airplane airplane;
    private String outboundDate;
    private String returnDate;
    private double price;
    private String tripType;
    private TicketStatus status;
    private SeatClass seatType;
    private String departure;
    private String destination;

    public Ticket(String ticketID, Airplane airplane, String outboundDate, String returnDate, double price, String tripType, TicketStatus status, SeatClass seatType, String departure, String destination) {
        this.ticketID = ticketID;
        this.passengerID = null;
        this.airplane = airplane;
        this.outboundDate = outboundDate;
        this.returnDate = returnDate;
        this.price = price;
        this.tripType = tripType;
        this.status = status;
        this.seatType = seatType;
        this.departure = departure;
        this.destination = destination;
    }
}
