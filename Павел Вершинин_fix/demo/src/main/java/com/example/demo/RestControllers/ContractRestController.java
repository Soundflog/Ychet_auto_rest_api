package com.example.demo.RestControllers;

import com.example.demo.Assemblers.ContractAssembler;
import com.example.demo.Entities.Autostops;
import com.example.demo.Entities.Contracts;
import com.example.demo.Exception.AllNotException;
import com.example.demo.Repositories.ContractsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/contracts")
public class ContractRestController {
    @Autowired
    ContractsRepository repository;
    @Autowired
    ContractAssembler assembler;

    @GetMapping("/all")
    public CollectionModel<EntityModel<Contracts>> all(){
        List<EntityModel<Contracts>> contract = repository.findAll()
                .stream()
                .map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(contract,
                linkTo(methodOn(ContractRestController.class).all()).withSelfRel());
    }
    @PostMapping("/all")
    ResponseEntity<?> newAutos(@RequestBody Contracts newOb){
        EntityModel<Contracts> entityModel = assembler.toModel(repository.save(newOb));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
    @GetMapping("/{id}")
    public EntityModel<Contracts> one(@PathVariable Long id){
        Contracts stops = repository.findById(id)
                .orElseThrow(()-> new AllNotException(id));
        return assembler.toModel(stops);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> replaceRoles (@RequestBody Contracts newOb, @PathVariable Long id){
        EntityModel<Contracts> entityModel = assembler.toModel(
                assembler.enrich(newOb, id));
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
