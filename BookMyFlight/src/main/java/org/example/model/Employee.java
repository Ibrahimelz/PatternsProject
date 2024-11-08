package org.example.model;

import lombok.*;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Employee extends User{
    private String position;
    private static Queue<Ticket> ticketsToCancel = new PriorityQueue<>();

    /*TODO
    public void removeTicket(Ticket ticket){

    }

    public void refund(Ticket ticket){

    }
    */
}
