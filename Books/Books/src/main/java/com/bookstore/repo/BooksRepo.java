package com.bookstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.bookstore.model.Books;

import java.util.List;

@Repository
public interface BooksRepo extends JpaRepository<Books, Integer>, JpaSpecificationExecutor<Books> {
    List<Books> findByBookNameContainingAndAuthorContainingAndStockBetween(
            String bookName, String author, Integer minStock, Integer maxStock);
}
