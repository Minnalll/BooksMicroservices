package com.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
public class AuthorDto {
    @NotNull
    private String name;
    @Past(message = "Date must be in the past")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
            message = "Invalid email format"
    )
    private String email;
}
