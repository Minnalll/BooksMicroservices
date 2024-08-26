package com.bookstore.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Books API",
                description = "Doing CRUD Operation for Books",
                summary = "This book-api will perform CRUD Operation on Books Micro Services",
                termsOfService = "T&C",
                contact = @Contact(
                        name = "Udaya Kumar",
                        email = "ukumar@benz.com"
                ),
                license = @License(
                        name = "License Number"
                ),
                version = "V1"
        ),
        servers = {
                @Server(
                        description = "Dev",
                        url = "http://localhost:3000"
                )
        }
)
public class OpenApiConfig {
}
