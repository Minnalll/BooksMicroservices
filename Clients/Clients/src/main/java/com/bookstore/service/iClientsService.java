package com.bookstore.service;

import com.bookstore.dto.ClientDto;
import com.bookstore.model.Client;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface iClientsService {

    public ClientDto getClient(int eCode);

    List<ClientDto> getClients();

    ClientDto createClients (ClientDto clientDto) throws Exception;

    ResponseEntity<Client> deleteClients (int eCode);

    ClientDto updateClients(int clientId, ClientDto clientDto);
}
