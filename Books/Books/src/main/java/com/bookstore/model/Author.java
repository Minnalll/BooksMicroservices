package com.bookstore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
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
                name = "dob",
                column = @Column(name = "author_dob")
        ),
        @AttributeOverride(
                name = "email",
                column = @Column(name = "author_email")
        )
})
public class Author {
    private String name;
    private LocalDate dob;
    private String email;
}
