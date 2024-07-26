package com.bookstore.utils;

import com.bookstore.model.Books;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

public class SearchSpecification {
    public static Specification<Books> getBooksByCriteria(String bookName, String author, LocalDate publishedOn, LocalDate purchasedOn, Integer minStock, Integer maxStock) {
        return (root, query, criteriaBuilder) -> {
            var predicates = criteriaBuilder.conjunction();

            if (StringUtils.hasText(bookName)) {
                predicates.getExpressions().add(criteriaBuilder.like(root.get("bookName"), "%" + bookName + "%"));
            }
            if (StringUtils.hasText(author)) {
                predicates.getExpressions().add(criteriaBuilder.like(root.get("author"), "%" + author + "%"));
            }
            if (publishedOn != null) {
                predicates.getExpressions().add(criteriaBuilder.equal(root.get("publishedOn"), publishedOn));
            }
            if (purchasedOn != null) {
                predicates.getExpressions().add(criteriaBuilder.equal(root.get("purchasedOn"), purchasedOn));
            }
            if (minStock != null) {
                predicates.getExpressions().add(criteriaBuilder.greaterThanOrEqualTo(root.get("stock"), minStock));
            }
            if (maxStock != null) {
                predicates.getExpressions().add(criteriaBuilder.lessThanOrEqualTo(root.get("stock"), maxStock));
            }

            return predicates;
        };
    }
}
