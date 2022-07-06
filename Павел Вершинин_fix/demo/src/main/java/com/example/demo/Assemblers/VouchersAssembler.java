package com.example.demo.Assemblers;

import com.example.demo.Entities.Vouchers;
import com.example.demo.RestControllers.StaffRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class VouchersAssembler implements RepresentationModelAssembler<Vouchers,
        EntityModel<Vouchers>> {
    @Override
    public EntityModel<Vouchers> toModel(Vouchers vouchers){
        return EntityModel.of(vouchers,
                linkTo(methodOn(StaffRestController.class).one(vouchers.getId())).withSelfRel(),
                linkTo(methodOn(StaffRestController.class).all()).withRel("staffs")
        );
    }
}
