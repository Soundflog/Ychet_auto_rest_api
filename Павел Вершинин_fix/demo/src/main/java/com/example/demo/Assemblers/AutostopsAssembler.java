package com.example.demo.Assemblers;

import com.example.demo.Entities.Autostops;
import com.example.demo.RestControllers.AutostopsRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class AutostopsAssembler implements RepresentationModelAssembler<Autostops,
        EntityModel<Autostops>> {
    @Override
    public EntityModel<Autostops> toModel(Autostops au){
        return EntityModel.of(au,
                linkTo(methodOn(AutostopsRestController.class).one(au.getId())).withSelfRel(),
                linkTo(methodOn(AutostopsRestController.class).all()).withRel("autostops")
        );
    }
}
