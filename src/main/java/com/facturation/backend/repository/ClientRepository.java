package com.facturation.backend.repository;

import com.facturation.backend.entity.Client;
import com.facturation.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    //Récupérer les clients
    List<Client> findClientsByUser(User user);
 }
