package com.example.demo.Assemblers;

import com.example.demo.Entities.Autos;
import com.example.demo.Repositories.AutosRepository;
import com.example.demo.RestControllers.AutosRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AutosAssembler implements RepresentationModelAssembler<Autos,
        EntityModel<Autos>> {
    @Override
    public EntityModel<Autos> toModel(Autos autos){
        return EntityModel.of(autos,
                linkTo(methodOn(AutosRestController.class).one(autos.getId())).withSelfRel(),
                linkTo(methodOn(AutosRestController.class).all()).withRel("autos")
        );
    }
    public Autos enrich (Autos newOb, Long id){
        return repository.findById(id)
                .map(autos -> {
                    autos.setAutoname(newOb.getAutoname());
                    return repository.save(autos);
                }).orElseGet(()-> {
                    newOb.setId(id);
                    return repository.save(newOb);
                });
    }
    @Autowired
    AutosRepository repository;
}
