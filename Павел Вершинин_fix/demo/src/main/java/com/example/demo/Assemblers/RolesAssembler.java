package com.example.demo.Assemblers;

import com.example.demo.Entities.Roles;
import com.example.demo.RestControllers.RolesRestController;
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
}
