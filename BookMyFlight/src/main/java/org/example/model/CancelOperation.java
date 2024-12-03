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
    private int refundedCredit;

    public CancelOperation(String passengerID, double refundedMoney, String ticketID, int refundedCredit) {
        super("Cancel Operation", LocalDateTime.now(),ticketID, passengerID);
        this.refundedMoney = refundedMoney;
        this.refundedCredit = refundedCredit;
    }
}
