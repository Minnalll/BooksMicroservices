package com.bookstore.dto;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@AttributeOverrides({
        @AttributeOverride(
                name = "name",
                column = @Column(name = "author_name")
        ),
        @AttributeOverride(
                name = "dOB",
                column = @Column(name = "author_dob")
        ),
        @AttributeOverride(
                name = "email",
                column = @Column(name = "author_email")
        )
})
public class AuthorDto {

    private String name;
    private LocalDate dOB;
    private String email;
}
