package com.example.demo.RestControllers;

import com.example.demo.Assemblers.AutosAssembler;
import com.example.demo.Entities.Autos;
import com.example.demo.Entities.Roles;
import com.example.demo.Exception.AllNotException;
import com.example.demo.Repositories.AutosRepository;
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
@RequestMapping("/autos")
public class AutosRestController {
    @Autowired
    AutosRepository autosRepository;
    @Autowired
    AutosAssembler assembler;


    @GetMapping("/all")
    public CollectionModel<EntityModel<Autos>> all(){
        List<EntityModel<Autos>> auto = autosRepository.findAll()
                .stream()
                .map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(auto,
                linkTo(methodOn(AutosRestController.class).all()).withSelfRel());
    }
    @PostMapping("/all")
    ResponseEntity<?> newAutos(@RequestBody Autos newAuto){
        EntityModel<Autos> entityModel = assembler.toModel(autosRepository.save(newAuto));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
    @GetMapping("/{id}")
    public EntityModel<Autos> one(@PathVariable Long id){
        Autos autos = autosRepository.findById(id)
                .orElseThrow(()-> new AllNotException(id));
        return assembler.toModel(autos);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> replaceRoles (@RequestBody Autos newAutos, @PathVariable Long id){
        Autos updated = autosRepository.findById(id)
                .map(autos -> {
                    autos.setAutoname(newAutos.getAutoname());
                    return autosRepository.save(autos);
                }).orElseGet(()-> {
                    newAutos.setId(id);
                    return autosRepository.save(newAutos);
                });
        EntityModel<Autos> entityModel = assembler.toModel(updated);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteRole(@PathVariable Long id){
        autosRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
