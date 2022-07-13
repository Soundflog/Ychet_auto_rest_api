package com.example.demo.RestControllers;

import com.example.demo.Assemblers.VouchersAssembler;
import com.example.demo.Entities.Staffs;
import com.example.demo.Entities.Vouchers;
import com.example.demo.Exception.AllNotException;
import com.example.demo.Repositories.StaffRepository;
import com.example.demo.Repositories.VouchersRepository;
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
@RequestMapping("/vouchers")
public class VouchersRestController {
    @Autowired
    VouchersRepository vouchersRepository;
    @Autowired
    VouchersAssembler assembler;

    @GetMapping("/all")
    public CollectionModel<EntityModel<Vouchers>> all(){
        List<EntityModel<Vouchers>> voucher = vouchersRepository.findAll()
                .stream()
                .map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(voucher,
                linkTo(methodOn(VouchersRestController.class).all()).withSelfRel());
    }
    @PostMapping("/all")
    ResponseEntity<?> newAutos(@RequestBody Vouchers newOb){
        EntityModel<Vouchers> entityModel = assembler.toModel(vouchersRepository.save(newOb));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
    @GetMapping("/{id}")
    public EntityModel<Vouchers> one(@PathVariable Long id){
        Vouchers voucher = vouchersRepository.findById(id)
                .orElseThrow(()-> new AllNotException(id));
        return assembler.toModel(voucher);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> replaceRoles (@RequestBody Vouchers newOb, @PathVariable Long id){
        EntityModel<Vouchers> entityModel = assembler.toModel(
                assembler.enrich(newOb, id));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteRole(@PathVariable Long id){
        vouchersRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
