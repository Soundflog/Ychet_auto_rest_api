package com.example.demo.Assemblers;

import com.example.demo.Entities.Discounts;
import com.example.demo.RestControllers.DiscountsRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class DiscountsAssembler implements RepresentationModelAssembler<Discounts,
        EntityModel<Discounts>> {
    @Override
    public EntityModel<Discounts> toModel(Discounts discounts){
        return EntityModel.of(discounts,
                linkTo(methodOn(DiscountsRestController.class).one(discounts.getId())).withSelfRel(),
                linkTo(methodOn(DiscountsRestController.class).all()).withRel("discounts")
        );
    }
}
