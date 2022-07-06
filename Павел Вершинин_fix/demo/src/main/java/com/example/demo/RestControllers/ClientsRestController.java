package com.example.demo.RestControllers;

import com.example.demo.Entities.Clients;
import com.example.demo.Repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
@RestController
public class ClientsRestController {
    @RequestMapping("/clients")
    Collection<Clients> clients() {
        return this.clientsRepository.findAll();
    }

    @Autowired
    ClientsRepository clientsRepository;
}
