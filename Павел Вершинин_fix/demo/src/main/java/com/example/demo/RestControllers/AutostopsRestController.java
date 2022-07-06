package com.example.demo.RestControllers;

import com.example.demo.Assemblers.AutostopsAssembler;
import com.example.demo.Entities.Autostops;
import com.example.demo.Entities.Staffs;
import com.example.demo.Exception.AllNotException;
import com.example.demo.Repositories.AutostopsRepository;
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
@RequestMapping("/autostops")
public class AutostopsRestController {
    @Autowired
    AutostopsRepository repository;
    @Autowired
    AutostopsAssembler assembler;

    @GetMapping("/all")
    public CollectionModel<EntityModel<Autostops>> all(){
        List<EntityModel<Autostops>> stops = repository.findAll()
                .stream()
                .map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(stops,
                linkTo(methodOn(AutostopsRestController.class).all()).withSelfRel());
    }
    @PostMapping("/all")
    ResponseEntity<?> newAutos(@RequestBody Autostops newOb){
        EntityModel<Autostops> entityModel = assembler.toModel(repository.save(newOb));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
    @GetMapping("/{id}")
    public EntityModel<Autostops> one(@PathVariable Long id){
        Autostops stops = repository.findById(id)
                .orElseThrow(()-> new AllNotException(id));
        return assembler.toModel(stops);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> replaceRoles (@RequestBody Autostops newOb, @PathVariable Long id){
        Autostops updated = repository.findById(id)
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
        EntityModel<Autostops> entityModel = assembler.toModel(updated);
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
