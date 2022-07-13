package com.example.demo.Assemblers;

import com.example.demo.Entities.Autostops;
import com.example.demo.Repositories.AutosRepository;
import com.example.demo.Repositories.AutostopsRepository;
import com.example.demo.RestControllers.AutostopsRestController;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Autostops enrich (Autostops newOb, Long id){
        return repository.findById(id)
                .map(stops -> {
                    stops.setName(newOb.getName());
                    stops.setAddress(newOb.getAddress());
                    stops.setSeats(newOb.getSeats());
                    stops.setType(newOb.getType());
                    return repository.save(stops);
                }).orElseGet(()-> {
                    newOb.setId(id);
                    return repository.save(newOb);
                });
    }

    @Autowired
    AutostopsRepository repository;
}
