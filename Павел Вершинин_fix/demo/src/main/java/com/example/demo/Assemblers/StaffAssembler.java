package com.example.demo.Assemblers;

import com.example.demo.Entities.Staffs;
import com.example.demo.RestControllers.StaffRestController;
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
}
