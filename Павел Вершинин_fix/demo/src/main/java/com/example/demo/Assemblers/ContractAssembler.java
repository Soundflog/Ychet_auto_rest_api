package com.example.demo.Assemblers;

import com.example.demo.Entities.Contracts;
import com.example.demo.Repositories.ContractsRepository;
import com.example.demo.RestControllers.ContractRestController;
import com.example.demo.RestControllers.StaffRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class ContractAssembler implements RepresentationModelAssembler<Contracts,
        EntityModel<Contracts>> {
    @Override
    public EntityModel<Contracts> toModel(Contracts au){
        return EntityModel.of(au,
                linkTo(methodOn(ContractRestController.class).one(au.getId())).withSelfRel(),
                linkTo(methodOn(ContractRestController.class).all()).withRel("contracts")
        );
    }
    public Contracts enrich (Contracts newOb, Long id){
        return repository.findById(id)
                .map(contract -> {
                    contract.setConclusionDate(newOb.getConclusionDate());
                    contract.setAutostops(newOb.getAutostops());
                    contract.setStaffs(newOb.getStaffs());
                    contract.setClients(newOb.getClients());
                    contract.setVouchers(newOb.getVouchers());
                    return repository.save(contract);
                }).orElseGet(()-> {
                    newOb.setId(id);
                    return repository.save(newOb);
                });
    }
    @Autowired
    ContractsRepository repository;
}
