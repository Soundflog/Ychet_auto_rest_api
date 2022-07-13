package com.example.demo.Assemblers;

import com.example.demo.Entities.Staffs;
import com.example.demo.Repositories.StaffRepository;
import com.example.demo.RestControllers.StaffRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class StaffAssembler implements RepresentationModelAssembler<Staffs,
        EntityModel<Staffs>> {
    @Override
    public EntityModel<Staffs> toModel(Staffs staffs){
        return EntityModel.of(staffs,
                linkTo(methodOn(StaffRestController.class).one(staffs.getId())).withSelfRel(),
                linkTo(methodOn(StaffRestController.class).all()).withRel("staffs")
        );
    }
    public Staffs enrich (Staffs newOb, Long id){
        return repository.findById(id)
                .map(staffs -> {
                    staffs.setFirstname(newOb.getFirstname());
                    staffs.setLastname(newOb.getLastname());
                    staffs.setMidname(newOb.getMidname());
                    staffs.setDesription(newOb.getDesription());
                    staffs.setRoles_staff(newOb.getRoles_staff());
                    return repository.save(staffs);
                }).orElseGet(()-> {
                    newOb.setId(id);
                    return repository.save(newOb);
                });
    }
    @Autowired
    StaffRepository repository;
}
