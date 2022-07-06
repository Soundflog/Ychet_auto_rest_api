package com.example.demo.Assemblers;

import com.example.demo.Entities.Clients;
import com.example.demo.RestControllers.ClientsRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class ClientsAssembler implements RepresentationModelAssembler<Clients,
        EntityModel<Clients>> {
    @Override
    public EntityModel<Clients> toModel(Clients cl){
        return EntityModel.of(cl,
                linkTo(methodOn(ClientsRestController.class).one(cl.getId())).withSelfRel(),
                linkTo(methodOn(ClientsRestController.class).all()).withRel("clients")
        );
    }
}
