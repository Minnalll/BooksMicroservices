package com.bookstore.dto;

import com.bookstore.model.Client;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    @NotNull
    private Integer clientId;
    private String clientName;
    private String address;
    private long phoneNumber;
    private String email;

    private List<BooksDto> books;
}