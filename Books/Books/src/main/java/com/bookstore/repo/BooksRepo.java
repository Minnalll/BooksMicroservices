package com.bookstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookstore.model.Books;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BooksRepo extends JpaRepository<Books, Integer>, JpaSpecificationExecutor<Books> {

//    @Query("SELECT b FROM Books b WHERE b.bookName LIKE %:bookName% OR b.publishedOn BETWEEN :startDate AND :endDate")
//    List<Books> findByBookNameContainingOrPublishedOnBetween(@Param("bookName") String bookName, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT b FROM Books b JOIN FETCH b.client WHERE b.bookId = :bookId")
    Books findBookWithClient(@Param("bookId") Integer bookId);
}
