package com.example.demo.RestControllers;

import com.example.demo.Assemblers.DiscountsAssembler;
import com.example.demo.Entities.Discounts;
import com.example.demo.Exception.AllNotException;
import com.example.demo.Repositories.DiscountsRepository;
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
@RequestMapping("/discounts")
public class DiscountsRestController {
    @Autowired
    DiscountsRepository discountsRepository;
    @Autowired
    DiscountsAssembler assembler;

    @GetMapping("/all")
    public CollectionModel<EntityModel<Discounts>> all(){
        List<EntityModel<Discounts>> discount = discountsRepository.findAll()
                .stream()
                .map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(discount,
                linkTo(methodOn(DiscountsRestController.class).all()).withSelfRel());
    }
    @PostMapping("/all")
    ResponseEntity<?> newAutos(@RequestBody Discounts newOb){
        EntityModel<Discounts> entityModel = assembler.toModel(discountsRepository.save(newOb));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
    @GetMapping("/{id}")
    public EntityModel<Discounts> one(@PathVariable Long id){
        Discounts discount = discountsRepository.findById(id)
                .orElseThrow(()-> new AllNotException(id));
        return assembler.toModel(discount);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> replaceRoles (@RequestBody Discounts newOb, @PathVariable Long id){
        EntityModel<Discounts> entityModel = assembler.toModel(
                assembler.enrich(newOb, id));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteRole(@PathVariable Long id){
        discountsRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
