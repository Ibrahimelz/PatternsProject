package org.example.controller;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CancelOperation extends Operation {
    private double refunded;

/*  public double obtainRefundedAmount(){ TODO

    }*/
}
