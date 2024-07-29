package com.bookstore.dto;

import java.time.LocalDate;

import com.bookstore.model.Author;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Embedded;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.format.annotation.NumberFormat;

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
	private Author author;
}
