package com.example.demo.LineRunners;

import com.example.demo.Entities.Clients;
import com.example.demo.Repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ClientsLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        for(Clients c : this.clientsRepository.findAll())
            System.out.println(c.toString());
    }
    @Autowired
    ClientsRepository clientsRepository;
}
