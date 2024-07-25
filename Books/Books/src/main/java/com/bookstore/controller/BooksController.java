package com.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//	@GetMapping


}
