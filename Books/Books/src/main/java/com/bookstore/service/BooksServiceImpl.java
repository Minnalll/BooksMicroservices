package com.bookstore.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.bookstore.utils.SearchSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookstore.dto.BooksDto;
import com.bookstore.exception.BadRequest;
import com.bookstore.exception.ResourceNotFoundException;
import com.bookstore.model.Books;
import com.bookstore.repo.BooksRepo;

@Service
public class BooksServiceImpl implements iBooksService{
	@Autowired
	private BooksRepo repo;

	ModelMapper modelMapper = new ModelMapper();
	
	public BooksDto getBook(int bookId) {
		try {
			Books getBooks = repo.findById(bookId).get();
			BooksDto dto = new BooksDto();
			BeanUtils.copyProperties(getBooks, dto);
			return dto;
		} catch (Exception e) {
			throw new ResourceNotFoundException("Book not found with requested ID.");
		}		 
	}

	@Override
	public List<BooksDto> getBooks() {
		List<Books> findAll = repo.findAll();
		if(findAll.isEmpty()) {
			throw new ResourceNotFoundException("No Books Found!!!");
		}
		return findAll.stream().map(book -> modelMapper.map(book, BooksDto.class)).collect(Collectors.toList());
	}
// Model Mapper implemented
	@Override
	public BooksDto createBooks(BooksDto booksDto) throws Exception {
		try {
			Books books = modelMapper.map(booksDto, Books.class);
			Books created = repo.save(books);
            return modelMapper.map(created, BooksDto.class);
		} catch (Exception e) {
			throw new Exception("Data not updated to DB");
		}		
	}

	@Override
	public ResponseEntity<Books> deleteBooks(int eCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BooksDto updateEmployee(int bookId, BooksDto booksDto) {
		try {
			BooksDto book = getBook(bookId);
			if (null != booksDto.getBookName()) {book.setBookName(booksDto.getBookName());}
			if (0 != booksDto.getStock()) {book.setStock(booksDto.getStock());}
			if (null != booksDto.getAuthor()) {book.setAuthor(booksDto.getAuthor());}
			if (null != booksDto.getPurchasedOn()) {book.setPurchasedOn(booksDto.getPurchasedOn());}
			if (null != booksDto.getPublishedOn()) {book.setPublishedOn(booksDto.getPublishedOn());}
			Books books = modelMapper.map(book, Books.class);
			Books updated = repo.save(books);
			BooksDto booksDto1 = modelMapper.map(updated, BooksDto.class);
			return booksDto1;
		} catch (Exception e) {
			throw new BadRequest("Student data not saved to DB");
		}
	}

	@Override
	public List<Books> findByBookNameContainingOrPublishedOnBetween(String bookName, LocalDate startDate, LocalDate endDate) {
		return repo.findByBookNameContainingOrPublishedOnBetween(bookName, startDate, endDate);
	}



}
