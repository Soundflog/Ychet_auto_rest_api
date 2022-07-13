package com.example.demo.Assemblers;

import com.example.demo.Entities.Authorizations;
import com.example.demo.Entities.Autostops;
import com.example.demo.Repositories.AuthorizationsRepository;
import com.example.demo.RestControllers.AuthorizationsRestController;
import com.example.demo.RestControllers.StaffRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class AuthorizationsAssembler implements RepresentationModelAssembler<Authorizations,
        EntityModel<Authorizations>> {
    @Override
    public EntityModel<Authorizations> toModel(Authorizations au){
        return EntityModel.of(au,
                linkTo(methodOn(AuthorizationsRestController.class).one(au.getId())).withSelfRel(),
                linkTo(methodOn(AuthorizationsRestController.class).all()).withRel("authorizations")
        );
    }
    public Authorizations enrich (Authorizations newOb, Long id){
        return repository.findById(id)
                .map(author -> {
                    author.setLogin(newOb.getLogin());
                    author.setPassword(newOb.getPassword());
                    return repository.save(author);
                }).orElseGet(()-> {
                    newOb.setId(id);
                    return repository.save(newOb);
                });
    }
    @Autowired
    AuthorizationsRepository repository;
}
