package org.example.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PaymentOperation extends Operation {
    private double totalPayment;
    private List<Integer> ticketIDList = new ArrayList<Integer>();
}
