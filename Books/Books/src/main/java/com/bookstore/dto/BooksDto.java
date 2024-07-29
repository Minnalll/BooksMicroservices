package com.bookstore.dto;

import java.time.LocalDate;

import com.bookstore.model.Author;
import jakarta.persistence.Embedded;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BooksDto {
	@NotNull
	private Integer bookId;
	private String bookName;
	private LocalDate publishedOn;
	private LocalDate purchasedOn;
	private int stock;

	@Embedded
	private Author author;
}
