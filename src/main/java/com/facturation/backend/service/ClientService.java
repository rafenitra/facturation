package com.facturation.backend.service;

import com.facturation.backend.entity.Client;
import com.facturation.backend.entity.User;
import com.facturation.backend.repository.ClientRepository;
import com.facturation.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ClientService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    //Ajout d'un client
    public Client addClient(Client client, String mailUser){
        User user = userRepository.findByMail(mailUser)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        client.setUser(user);
        return clientRepository.save(client);
    }

    //Lister les clients du freelance
    public List<Client> getClients (String mail){
        User user = userRepository.findByMail(mail)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        return  clientRepository.findClientsByUser(user);
    }

    //Modifier un client
    public Client updateClient(Long idClient, Client updatedClient, String mailUser){
        Client existingClient = clientRepository.findById(idClient)
                .orElseThrow(() -> new RuntimeException("Client introuvable"));
        if(!existingClient.getUser().getMail().equals(mailUser)){
            throw new RuntimeException("Non autorisé");
        }
        //Mise à jour
        existingClient.setName(updatedClient.getName());
        existingClient.setAddress(updatedClient.getAddress());
        existingClient.setCompany(updatedClient.getCompany());
        existingClient.setMail(updatedClient.getMail());

        return clientRepository.save(existingClient);
    }


    //suppression d'un clizent
    public void deleteClient(Long idClient, String mailUser){
        Client existingClient = clientRepository.findById(idClient)
                .orElseThrow(() -> new RuntimeException("Client introuvable"));
        if(!existingClient.getUser().getMail().equals(mailUser))
            throw new RuntimeException("Non autorisé");

        //deletion du client
        clientRepository.deleteById(idClient);
    }
}
