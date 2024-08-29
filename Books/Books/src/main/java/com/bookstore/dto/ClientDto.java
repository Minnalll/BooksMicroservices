package com.bookstore.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;


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

}
