package com.ecommerce.reactivemoviehub.dto.request.actor;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class ActorRequestDto {
    @NotEmpty(message = "Actor name cannot be empty")
    @Size(max =100, message = "Actor name is too long")
    private String firstName;

    @NotEmpty(message = "Actor last name cannot be empty")
    @Size(max =100, message = "Actor last name is too long")
    private String lastName;

    @NotNull(message = "Actor birth date cannot be null")
    @Past
    private LocalDate birthDate;

    @Size(max =500, message = "Bio is too long")
    @NotEmpty(message = "Bio name cannot be empty")
    private String bio;
}
