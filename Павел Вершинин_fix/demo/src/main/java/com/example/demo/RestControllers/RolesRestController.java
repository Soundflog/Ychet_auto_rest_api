package com.example.demo.RestControllers;

import com.example.demo.Entities.Roles;
import com.example.demo.Exception.AllNotException;
import com.example.demo.Repositories.RolesRepository;
import com.example.demo.Assemblers.RolesAssembler;
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
@RequestMapping("/roles")
public class RolesRestController {
    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    RolesAssembler assembler;

    @GetMapping("/all")
    public CollectionModel<EntityModel<Roles>> all(){
       List<EntityModel<Roles>> roles = rolesRepository.findAll()
               .stream()
               .map(assembler::toModel).collect(Collectors.toList());
       return CollectionModel.of(roles,
               linkTo(methodOn(RolesRestController.class).all()).withSelfRel());
    }
    @PostMapping("/all")
    ResponseEntity<?> newRoles(@RequestBody Roles newRoles){
        EntityModel<Roles> entityModel = assembler.toModel(rolesRepository.save(newRoles));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }


    @GetMapping("/{id}")
    public EntityModel<Roles> one(@PathVariable Long id){
        Roles roles = rolesRepository.findById(id)
                .orElseThrow(()-> new AllNotException(id));
        return assembler.toModel(roles);
    }


    @PutMapping("/{id}")
    ResponseEntity<?> replaceRoles (@RequestBody Roles newRoles, @PathVariable Long id){
        EntityModel<Roles> entityModel = assembler.toModel(
                assembler.enrich(newRoles, id));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteRole(@PathVariable Long id){
        rolesRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
