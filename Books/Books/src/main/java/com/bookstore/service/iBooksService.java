package com.bookstore.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import com.bookstore.dto.BooksDto;
import com.bookstore.model.Books;

public interface iBooksService {
	
	public BooksDto getBook(int eCode);
	
	List<BooksDto> getBooks();
	
	BooksDto createBooks (BooksDto booksDto) throws Exception;
	
	ResponseEntity<Books> deleteBooks (int eCode);

	BooksDto updateEmployee(int bookId, BooksDto bookDto);

	public List<Books> findByBookNameContainingOrPublishedOnBetween(String bookName, LocalDate startDate, LocalDate endDate);

}
