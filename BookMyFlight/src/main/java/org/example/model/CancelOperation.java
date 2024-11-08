package org.example.model;

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
