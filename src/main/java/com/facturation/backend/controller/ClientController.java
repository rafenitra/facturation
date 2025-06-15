package com.facturation.backend.controller;

import com.facturation.backend.entity.Client;
import com.facturation.backend.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    //appelle de la fonction d'ajout d'un client
    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody Client client, Authentication authentication){
        String mailUser = authentication.getName();
        Client savedClient = clientService.addClient(client, mailUser);
        return  ResponseEntity.ok(savedClient);
    }

    @GetMapping
    public ResponseEntity<List<Client>> getClients(Authentication authentication){
        String mailUser = authentication.getName();
        List<Client> clients = clientService.getClients(mailUser);
        return ResponseEntity.ok(clients);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@RequestBody Client updatedClient, @PathVariable Long id, Authentication authentication){
        String mailUser = authentication.getName();
        Client foundClient = clientService.updateClient(id,updatedClient,mailUser);
        return ResponseEntity.ok(foundClient);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id, Authentication authentication){
        String mailUser = authentication.getName();
        clientService.deleteClient(id, mailUser);
        return ResponseEntity.noContent().build();
    }

}
