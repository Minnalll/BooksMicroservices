package com.bookstore.dto;

import com.bookstore.model.Author;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BooksDto {
	@NotNull
	private Integer bookId;
	private String bookName;
	@Past(message = "Date must be in the past")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate publishedOn;
	@Past(message = "Date must be in the past")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate purchasedOn;
	@NumberFormat
	private int stock;

	@Embedded
	private AuthorDto author;

}
