package com.ecommerce.moviecore.dto.request.actor;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActorUpdateDto {
    @Size(max = 100, message = "Actor name is too long")
    private String firstName;

    @Size(max = 100, message = "Actor last name is too long")
    private String lastName;

    @Past
    private LocalDate birthDate;

    @Size(max = 500, message = "Bio is too long")
    private String bio;
}
