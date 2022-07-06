package com.example.demo.RestControllers;

import com.example.demo.Entities.Authorizations;
import com.example.demo.Entities.Roles;
import com.example.demo.Repositories.AuthorizationsRepository;
import com.example.demo.Repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
@RestController
public class RolesRestController {
    @RequestMapping("/roles")
    Collection<Roles> roles() {
        return this.rolesRepository.findAll();
    }

    @Autowired
    RolesRepository rolesRepository;
}
