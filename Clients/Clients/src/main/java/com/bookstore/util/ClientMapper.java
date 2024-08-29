package com.bookstore.util;

import com.bookstore.dto.AuthorDto;
import com.bookstore.dto.BooksDto;
import com.bookstore.dto.ClientDto;
import com.bookstore.model.Author;
import com.bookstore.model.Books;
import com.bookstore.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    // Mapping Client entity to ClientDto
    @Mapping(source = "clientId", target = "clientId")
    @Mapping(source = "clientName", target = "clientName")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "books", target = "books")
    ClientDto toClientDto(Client client);

    // Mapping Books entity to BooksDto
    @Mapping(source = "bookId", target = "bookId")
    @Mapping(source = "bookName", target = "bookName")
    @Mapping(source = "publishedOn", target = "publishedOn")
    @Mapping(source = "purchasedOn", target = "purchasedOn")
    @Mapping(source = "stock", target = "stock")
    @Mapping(source = "author", target = "author")
    BooksDto toBooksDto(Books book);

    // Mapping Author entity to AuthorDto (no specific mapping needed because fields match exactly)
    AuthorDto toAuthorDto(Author author);

    // List mappings
    List<BooksDto> toBooksDtoList(List<Books> books);
}

