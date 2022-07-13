package com.example.demo.Assemblers;

import com.example.demo.Entities.Vouchers;
import com.example.demo.Repositories.VouchersRepository;
import com.example.demo.RestControllers.StaffRestController;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Vouchers enrich (Vouchers newOb, Long id){
        return repository.findById(id)
                .map(voucher -> {
                    voucher.setIndate(newOb.getIndate());
                    voucher.setOutdate(newOb.getOutdate());
                    voucher.setSumm(newOb.getSumm());
                    voucher.setDiscounts(newOb.getDiscounts());
                    return repository.save(voucher);
                }).orElseGet(()-> {
                    newOb.setId(id);
                    return repository.save(newOb);
                });
    }
    @Autowired
    VouchersRepository repository;
}
