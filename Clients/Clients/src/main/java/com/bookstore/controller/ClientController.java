package com.bookstore.controller;

import com.bookstore.dto.ClientDto;
import com.bookstore.service.ClientsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    @Autowired
    private ClientsServiceImpl serv;

    @GetMapping("/find/{clientId}")
    public ResponseEntity<ClientDto> findOne(@PathVariable int clientId){
        ClientDto client = serv.getClient(clientId);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<List<ClientDto>> findAll(){
        List<ClientDto> client = serv.getClients();
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto) throws Exception{
        ClientDto createClient = serv.createClients(clientDto);
        return new ResponseEntity<>(createClient, HttpStatus.CREATED);
    }

    //update method
    @PutMapping("update/{clientId}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable int clientId, @RequestBody ClientDto clientDto){
        ClientDto updatedEmployee = serv.updateClients(clientId, clientDto);
        return new ResponseEntity<> (updatedEmployee, HttpStatus.CREATED);
    }
}
