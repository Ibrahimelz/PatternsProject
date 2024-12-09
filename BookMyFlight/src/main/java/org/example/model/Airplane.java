package org.example.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Airplane {
    private String airplaneID;
    private String assignedAirline;
    private int availableSeats;
    private String type;
    private static int counter = 1;

    public Airplane(String assignedAirline, int availableSeats, String type) {
        this.airplaneID = generateId();
        this.assignedAirline = assignedAirline;
        this.availableSeats = availableSeats;
        this.type = type;
    }

    private static String generateId() {
        return String.format("000%02d", counter++);
    }
}
