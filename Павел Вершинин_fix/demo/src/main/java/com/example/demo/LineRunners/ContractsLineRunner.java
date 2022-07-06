package com.example.demo.LineRunners;

import com.example.demo.Entities.Contracts;
import com.example.demo.Repositories.ContractsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ContractsLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        for(Contracts c: this.contractsRepository.findAll())
            System.out.println(c.toString());
    }
    @Autowired
    ContractsRepository contractsRepository;
}
