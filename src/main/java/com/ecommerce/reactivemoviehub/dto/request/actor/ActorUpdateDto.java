package com.ecommerce.reactivemoviehub.dto.request.actor;

import jakarta.validation.constraints.Size;


public class ActorUpdateDto {
    @Size(max =100, message = "Actor name is too long")
    private String name;

    @Size(max =500, message = "Bio is too long")
    private String bio;
}
