package com.example.demo.Assemblers;

import com.example.demo.Entities.Clients;
import com.example.demo.Repositories.ClientsRepository;
import com.example.demo.RestControllers.ClientsRestController;
import org.springframework.beans.factory.annotation.Autowired;
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
                linkTo(methodOn(ClientsRestController.class).all()).withRel("clients"));
    }

    public Clients enrich (Clients newOb, Long id){
        return repository.findById(id)
                .map(client -> {
                    client.setFirstname(newOb.getFirstname());
                    client.setLastname(newOb.getLastname());
                    client.setMidname(newOb.getMidname());
                    client.setGosnumber(newOb.getGosnumber());
                    client.setAutos_cl(newOb.getAutos_cl());
                    client.setNumberphone(newOb.getNumberphone());
                    client.setSeriaNumberDoc(newOb.getSeriaNumberDoc());
                    client.setRoles_client(newOb.getRoles_client());
                    return repository.save(client);
                }).orElseGet(()-> {
                    newOb.setId(id);
                    return repository.save(newOb);
                });
    }
    @Autowired
    ClientsRepository repository;
}
