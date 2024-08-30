package com.bookstore.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import ch.qos.logback.classic.Level;
import com.bookstore.exception.ApplicationException;
import com.bookstore.model.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class BooksServiceImpl implements iBooksService {

	private static final Logger logger = LoggerFactory.getLogger(BooksServiceImpl.class);

	@Autowired
	private EntityManager entityManager;
	@Autowired
	private BooksRepo repo;

	private static final String AUTHOR_FIELD = "author";

	ModelMapper modelMapper = new ModelMapper();

	public BooksDto getBook(int bookId) {
		logger.debug("Entering method getBook with parameters: {}", bookId);
		try {
			return repo.findById(bookId)
					.map(book -> {
						BooksDto dto = new BooksDto();
						BeanUtils.copyProperties(book, dto);
						logger.info("Books with ID {} registered successfully.", bookId);
						return dto;
					})
					.orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + bookId));
		} catch (ResourceNotFoundException e) {
			logger.error("Book with ID {} not found.", bookId, e);
			throw e;
		} catch (Exception e) {
			logger.error("An unexpected error occurred while fetching the book with ID {}.", bookId, e); // Log the full exception
			throw new ApplicationException("An error occurred while fetching the book. Please try again later.", e);
		}
	}

	@Override
	public List<BooksDto> getBooks() {
		List<Books> findAll = repo.findAll();
		if (findAll.isEmpty()) {
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
		return null;
	}

	@Override
	public BooksDto updateEmployee(int bookId, BooksDto booksDto) {
		try {
			BooksDto book = getBook(bookId);
			if (null != booksDto.getBookName()) {
				book.setBookName(booksDto.getBookName());
			}
			if (0 != booksDto.getStock()) {
				book.setStock(booksDto.getStock());
			}
			if (null != booksDto.getPurchasedOn()) {
				book.setPurchasedOn(booksDto.getPurchasedOn());
			}
			if (null != booksDto.getPublishedOn()) {
				book.setPublishedOn(booksDto.getPublishedOn());
			}
			if (booksDto.getAuthor() != null) {
				if (booksDto.getAuthor().getName() != null) {
					book.getAuthor().setName(booksDto.getAuthor().getName());
				}
				if (booksDto.getAuthor().getDob() != null) {
					book.getAuthor().setDob(booksDto.getAuthor().getDob());
				}
				if (booksDto.getAuthor().getEmail() != null) {
					book.getAuthor().setEmail(booksDto.getAuthor().getEmail());
				}
			}
			Books books = modelMapper.map(book, Books.class);
			Books updated = repo.save(books);
			return modelMapper.map(updated, BooksDto.class);
		} catch (Exception e) {
			throw new BadRequest("Student data not saved to DB");
		}
	}

//	@Override
//	public List<Books> findByBookNameContainingOrPublishedOnBetween(String bookName, LocalDate startDate, LocalDate endDate) {
//		return repo.findByBookNameContainingOrPublishedOnBetween(bookName, startDate, endDate);
//	}

	public List<Books> findBooks(String bookName, LocalDate publishedOnStart, LocalDate publishedOnEnd, Author author) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Books> query = cb.createQuery(Books.class);
		Root<Books> book = query.from(Books.class);

		Predicate predicate = cb.conjunction();

		if (bookName != null && !bookName.isEmpty()) {
//			predicate = cb.and(predicate, cb.equal(book.get("bookName"), bookName));
			predicate = cb.and(predicate, cb.like(book.get("bookName"), "%" + bookName + "%"));
		}
		if (publishedOnStart != null) {
			predicate = cb.and(predicate, cb.greaterThanOrEqualTo(book.get("publishedOn"), publishedOnStart));
		}
		if (publishedOnEnd != null) {
			predicate = cb.and(predicate, cb.lessThanOrEqualTo(book.get("publishedOn"), publishedOnEnd));
		}
		if (author != null) {
			if (author.getName() != null && !author.getName().isEmpty()) {
				predicate = cb.and(predicate, cb.like(book.get(AUTHOR_FIELD).get("name"), "%" + author.getName() + "%"));
			}
			if (author.getDob() != null) {
				predicate = cb.and(predicate, cb.equal(book.get(AUTHOR_FIELD).get("dOB"), author.getDob()));
			}
			if (author.getEmail() != null && !author.getEmail().isEmpty()) {
				predicate = cb.and(predicate, cb.like(book.get(AUTHOR_FIELD).get("email"), "%" + author.getEmail() + "%"));
			}
		}
			query.where(predicate);

			return entityManager.createQuery(query).getResultList();
	}
}
