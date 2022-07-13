package com.example.demo.RestControllers;

import com.example.demo.Assemblers.AuthorizationsAssembler;
import com.example.demo.Entities.Authorizations;
import com.example.demo.Entities.Autostops;
import com.example.demo.Exception.AllNotException;
import com.example.demo.Repositories.AuthorizationsRepository;
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
@RequestMapping("/authorization")
public class AuthorizationsRestController {
    @Autowired
    AuthorizationsRepository repository;
    @Autowired
    AuthorizationsAssembler assembler;

    @GetMapping("/all")
    public CollectionModel<EntityModel<Authorizations>> all(){
        List<EntityModel<Authorizations>> author = repository.findAll()
                .stream()
                .map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(author,
                linkTo(methodOn(AuthorizationsRestController.class).all()).withSelfRel());
    }
    @PostMapping("/all")
    ResponseEntity<?> newAutos(@RequestBody Authorizations newOb){
        EntityModel<Authorizations> entityModel = assembler.toModel(repository.save(newOb));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
    @GetMapping("/{id}")
    public EntityModel<Authorizations> one(@PathVariable Long id){
        Authorizations authorizations = repository.findById(id)
                .orElseThrow(()-> new AllNotException(id));
        return assembler.toModel(authorizations);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> replaceRoles (@RequestBody Authorizations newOb, @PathVariable Long id){
        EntityModel<Authorizations> entityModel = assembler.toModel(
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
