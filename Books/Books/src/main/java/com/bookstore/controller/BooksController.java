package com.bookstore.controller;

import java.time.LocalDate;
import java.util.List;

import com.bookstore.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bookstore.dto.BooksDto;
import com.bookstore.model.Books;
import com.bookstore.service.BooksServiceImpl;

@RestController
@RequestMapping("/api/v1/book")
public class BooksController {
	@Autowired
	private BooksServiceImpl serv;
	
	@GetMapping("/find/{bookId}")
	public ResponseEntity<BooksDto> findOne(@PathVariable int bookId){
		BooksDto books = serv.getBook(bookId);
		 return new ResponseEntity<>(books, HttpStatus.OK);
	}
	// Update
	@GetMapping("/find")
	public ResponseEntity<List<BooksDto>> findAll(){
		 List<BooksDto> books = serv.getBooks();
		 return new ResponseEntity<>(books, HttpStatus.OK);
	}


	@PostMapping("/create")
	public ResponseEntity<BooksDto> createBooks(@RequestBody BooksDto bookDto) throws Exception{		
		 BooksDto createBooks = serv.createBooks(bookDto);	
		 return new ResponseEntity<BooksDto> (createBooks, HttpStatus.CREATED);
	}

	//update method
	@PutMapping("update/{bookId}")
    public ResponseEntity<BooksDto> Update(@PathVariable int bookId, @RequestBody BooksDto bookDto){
		BooksDto updatedEmployee = serv.updateEmployee(bookId, bookDto);
		return new ResponseEntity<BooksDto> (updatedEmployee, HttpStatus.CREATED);
    }

//	@GetMapping("/search")
//	public ResponseEntity<List<Books>> searchBooks( @RequestParam("bookName") String bookName,
//									@RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
//									@RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
//
//		return new ResponseEntity<>(serv.findByBookNameContainingOrPublishedOnBetween(bookName, startDate, endDate), HttpStatus.OK);
//	}

	@GetMapping("/search")
	public List<Books> searchBooks(
			@RequestParam(required = false) String bookName,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate publishedOnStart,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate publishedOnEnd,
			@RequestParam(required = false) String authorName,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate authorDOB,
			@RequestParam(required = false) String authorEmail) {

		Author author = new Author();
		if (authorName != null && !authorName.isEmpty()) author.setName(authorName);
		if (authorDOB != null) author.setDOB(authorDOB);
		if (authorEmail != null && !authorEmail.isEmpty()) author.setEmail(authorEmail);

		return serv.findBooks(bookName, publishedOnStart, publishedOnEnd, author);
	}
}
