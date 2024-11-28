package org.example.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public abstract class Operation {
    private String type;
    private LocalDateTime date;
    private String userId;
}
