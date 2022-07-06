package com.example.demo.Assemblers;

import com.example.demo.Entities.Authorizations;
import com.example.demo.Entities.Autostops;
import com.example.demo.Repositories.AuthorizationsRepository;
import com.example.demo.RestControllers.AuthorizationsRestController;
import com.example.demo.RestControllers.StaffRestController;
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
}
