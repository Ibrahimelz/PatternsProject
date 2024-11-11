package org.example.controller;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PaymentOperation extends Operation {
    private double totalPayment;
    private List<Integer> ticketIDList;

    public PaymentOperation(double totalPayment) {
        this.totalPayment = totalPayment;
        this.ticketIDList = new ArrayList<Integer>();
    }
}
