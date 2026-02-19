package com.ecommerce.reactivemoviehub.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActorResponseDto {
    private String id;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String bio;
}
