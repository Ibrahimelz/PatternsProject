package org.example.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Ticket {
    @Getter
    @Setter
    private String ticketID;
    @Getter
    @Setter
    private String passengerID;
    @Getter
    @Setter
    private Airplane airplane;
    @Getter
    @Setter
    private String outboundDate;
    @Getter
    @Setter
    private String returnDate;
    @Getter
    @Setter
    private double price;
    @Getter
    @Setter
    private String tripType;
    @Getter
    @Setter
    private TicketStatus status;
    @Getter
    @Setter
    private SeatClass seatType;
    @Getter
    @Setter
    private String departure;
    @Getter
    @Setter
    private String destination;

    private static int counter = 1;

    public Ticket(Airplane airplane, String outboundDate, String returnDate, double price,
                  String tripType, TicketStatus status, SeatClass seatType, String departure, String destination) {
        this.ticketID = generateId();
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

    private static String generateId() {
        return String.format("000%02d", counter++);
    }
}
