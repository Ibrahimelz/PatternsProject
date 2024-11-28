package org.example.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Airplane {
    private int airplaneID;
    private String assignedAirline;
    private int availableSeats;
    private String type;
}
