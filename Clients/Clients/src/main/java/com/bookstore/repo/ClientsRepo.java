package com.bookstore.repo;

import com.bookstore.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepo extends JpaRepository<Client, Integer> {

}
