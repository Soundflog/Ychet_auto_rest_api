package com.example.demo.RestControllers;

import com.example.demo.Assemblers.ClientsAssembler;
import com.example.demo.Entities.Clients;
import com.example.demo.Exception.AllNotException;
import com.example.demo.Repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/clients")
public class ClientsRestController {
    @Autowired
    ClientsRepository repository;
    @Autowired
    ClientsAssembler assembler;

    @GetMapping("/all")
    public CollectionModel<EntityModel<Clients>> all(){
        List<EntityModel<Clients>> cl = repository.findAll()
                .stream()
                .map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(cl,
                linkTo(methodOn(ClientsRestController.class).all()).withSelfRel());
    }
    @PostMapping("/all")
    ResponseEntity<?> newAutos(@RequestBody Clients newOb){
        EntityModel<Clients> entityModel = assembler.toModel(repository.save(newOb));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
    @GetMapping("/{id}")
    public EntityModel<Clients> one(@PathVariable Long id){
        Clients clients = repository.findById(id)
                .orElseThrow(()-> new AllNotException(id));
        return assembler.toModel(clients);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> replaceRoles (@RequestBody Clients newOb, @PathVariable Long id){
        Clients updated = repository.findById(id)
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
        EntityModel<Clients> entityModel = assembler.toModel(updated);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteRole(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
