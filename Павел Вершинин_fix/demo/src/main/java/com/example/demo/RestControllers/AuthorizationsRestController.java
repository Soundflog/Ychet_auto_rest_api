package com.example.demo.RestControllers;

import com.example.demo.Entities.Authorizations;
import com.example.demo.Repositories.AuthorizationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
class AuthorizationsRestController {
    @RequestMapping("/authorization")
    Collection<Authorizations> authorizations() {
        return this.authorizationsRepository.findAll();
    }

    @Autowired
    AuthorizationsRepository authorizationsRepository;
}
