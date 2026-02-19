package com.ecommerce.reactivemoviehub.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
@CompoundIndex(
        def = "{'firstName': 1, 'lastName': 1, 'birthDate': 1}",
        unique = true
)
public class Actor {
    @Id
    private String id;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String bio;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
