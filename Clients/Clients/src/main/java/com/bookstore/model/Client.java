package com.bookstore.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer clientId;
    private String clientName;
    private String address;
    private long phoneNumber;
    private String email;
}
