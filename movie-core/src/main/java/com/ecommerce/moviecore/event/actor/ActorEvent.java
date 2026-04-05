package com.ecommerce.moviecore.event.actor;

import com.ecommerce.moviecore.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActorEvent {
    private String id;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String bio;

    private EventType actorEvent;
}
