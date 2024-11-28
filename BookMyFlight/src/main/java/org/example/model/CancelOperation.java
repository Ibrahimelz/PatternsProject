package org.example.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CancelOperation extends Operation {
    private double refundedMoney;
    private String ticketID;
    private int refundedCredit;

    public CancelOperation(String passengerID, double refundedMoney, String ticketID, int refundedCredit) {
        super("Cancel Operation", LocalDateTime.now(), passengerID);
        this.refundedMoney = refundedMoney;
        this.ticketID = ticketID;
        this.refundedCredit = refundedCredit;
    }
}
