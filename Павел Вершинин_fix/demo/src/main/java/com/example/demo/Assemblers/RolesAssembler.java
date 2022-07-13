package com.example.demo.Assemblers;

import com.example.demo.Entities.Roles;
import com.example.demo.Repositories.RolesRepository;
import com.example.demo.RestControllers.RolesRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RolesAssembler implements RepresentationModelAssembler<Roles,
        EntityModel<Roles>> {
    @Override
    public EntityModel<Roles> toModel(Roles roles){
        return EntityModel.of(roles,
                linkTo(methodOn(RolesRestController.class).one(roles.getId())).withSelfRel(),
                linkTo(methodOn(RolesRestController.class).all()).withRel("roles")
               );
    }
    public Roles enrich (Roles newOb, Long id){
        return repository.findById(id)
                .map(roles -> {
                    roles.setNamerole(newOb.getNamerole());
                    return repository.save(roles);
                }).orElseGet(()-> {
                    newOb.setId(id);
                    return repository.save(newOb);
                });
    }

    @Autowired
    RolesRepository repository;
}
