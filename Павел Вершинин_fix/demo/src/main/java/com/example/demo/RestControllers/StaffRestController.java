package com.example.demo.RestControllers;

import com.example.demo.Assemblers.StaffAssembler;
import com.example.demo.Entities.Autos;
import com.example.demo.Entities.Staffs;
import com.example.demo.Exception.AllNotException;
import com.example.demo.Repositories.StaffRepository;
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
@RequestMapping("/staffs")
public class StaffRestController {
    final
    StaffRepository staffRepository;
    @Autowired
    StaffAssembler assembler;

    public StaffRestController(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @GetMapping("/all")
    public CollectionModel<EntityModel<Staffs>> all(){
        List<EntityModel<Staffs>> staff = staffRepository.findAll()
                .stream()
                .map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(staff,
                linkTo(methodOn(StaffRestController.class).all()).withSelfRel());
    }
    @PostMapping("/all")
    ResponseEntity<?> newAutos(@RequestBody Staffs newOb){
        EntityModel<Staffs> entityModel = assembler.toModel(staffRepository.save(newOb));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
    @GetMapping("/{id}")
    public EntityModel<Staffs> one(@PathVariable Long id){
        Staffs staff = staffRepository.findById(id)
                .orElseThrow(()-> new AllNotException(id));
        return assembler.toModel(staff);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> replaceRoles (@RequestBody Staffs newOb, @PathVariable Long id){
        EntityModel<Staffs> entityModel = assembler.toModel(
                assembler.enrich(newOb, id));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteRole(@PathVariable Long id){
        staffRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
