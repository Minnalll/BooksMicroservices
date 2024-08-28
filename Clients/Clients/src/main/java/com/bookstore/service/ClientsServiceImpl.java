package com.bookstore.service;

import com.bookstore.dto.ClientDto;
import com.bookstore.exception.BadRequest;
import com.bookstore.exception.ResourceNotFoundException;
import com.bookstore.model.Client;
import com.bookstore.repo.ClientsRepo;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientsServiceImpl implements  iClientsService{

    @Autowired
    private ClientsRepo repo;

    ModelMapper modelMapper = new ModelMapper();

    public ClientDto getClient(int clientId) {
        try {
            Client getClients = repo.findById(clientId).get();
            ClientDto dto = new ClientDto();
            BeanUtils.copyProperties(getClients, dto);
            return dto;
        } catch (Exception e) {
            throw new ResourceNotFoundException("Client not found with requested ID.");
        }
    }

    @Override
    public List<ClientDto> getClients() {
        List<Client> findAll = repo.findAll();
        if (findAll.isEmpty()) {
            throw new ResourceNotFoundException("No Books Found!!!");
        }
        return findAll.stream().map(book -> modelMapper.map(book, ClientDto.class)).collect(Collectors.toList());
    }

    // Model Mapper implemented
    @Override
    public ClientDto createClients(ClientDto clientDto) throws Exception {
        try {
            Client client = modelMapper.map(clientDto, Client.class);
            Client created = repo.save(client);
            return modelMapper.map(created, ClientDto.class);
        } catch (Exception e) {
            throw new Exception("Data not updated to DB");
        }
    }

    @Override
    public ResponseEntity<Client> deleteClients(int eCode) {
        return null;
    }

    @Override
    public ClientDto updateClients(int clientId, ClientDto clientDto) {
        try {
            ClientDto client = getClient(clientId);
            if (null != clientDto.getClientName()) {
                client.setClientName(clientDto.getClientName());
            }
            if (0 != clientDto.getPhoneNumber()) {
                client.setPhoneNumber(clientDto.getPhoneNumber());
            }
            if (null != clientDto.getAddress()) {
                client.setAddress(clientDto.getAddress());
            }
            if (null != clientDto.getEmail()) {
                client.setEmail(clientDto.getEmail());
            }
            Client clients = modelMapper.map(client, Client.class);
            Client updated = repo.save(clients);
            return modelMapper.map(updated, ClientDto.class);
        } catch (Exception e) {
            throw new BadRequest("Student data not saved to DB");
        }
    }
}
