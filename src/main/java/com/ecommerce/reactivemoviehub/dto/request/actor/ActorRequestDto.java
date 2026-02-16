package com.ecommerce.reactivemoviehub.dto.request.actor;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActorRequestDto {
    @NotEmpty(message = "Actor name cannot be empty")
    @Size(max =100, message = "Actor name is too long")
    private String name;

    @Size(max =500, message = "Bio is too long")
    @NotEmpty(message = "Bio name cannot be empty")
    private String bio;
}
