package com.bookstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.model.Books;
@Repository
public interface BooksRepo extends JpaRepository<Books, Integer>{

}
