package com.example.demo.RestControllers;

import com.example.demo.Entities.Contracts;
import com.example.demo.Repositories.ContractsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ContractRestController {
    @RequestMapping("/contracts")
    Collection<Contracts> contracts() {
        return this.contractsRepository.findAll();
    }

    @Autowired
    ContractsRepository contractsRepository;
}
