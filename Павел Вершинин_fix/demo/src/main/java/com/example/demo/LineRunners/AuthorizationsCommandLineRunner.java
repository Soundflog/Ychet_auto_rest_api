package com.example.demo.LineRunners;

import com.example.demo.Entities.Authorizations;
import com.example.demo.Repositories.AuthorizationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class AuthorizationsCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        for (Authorizations a : this.authorizationsRepository.findAll())
            System.out.println(a.toString());
    }

    @Autowired
    AuthorizationsRepository authorizationsRepository;
}
