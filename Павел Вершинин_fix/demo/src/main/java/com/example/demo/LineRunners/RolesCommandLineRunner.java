package com.example.demo.LineRunners;

import com.example.demo.Entities.Authorizations;
import com.example.demo.Entities.Roles;
import com.example.demo.Repositories.AuthorizationsRepository;
import com.example.demo.Repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RolesCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        for (Roles a : this.rolesRepository.findAll())
            System.out.println(a.toString());
    }

    @Autowired
    RolesRepository rolesRepository;
}
