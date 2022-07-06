package com.example.demo.RestControllers;

import com.example.demo.Entities.Discounts;
import com.example.demo.Entities.Staffs;
import com.example.demo.Repositories.DiscountsRepository;
import com.example.demo.Repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class DiscountsRestController {
    @RequestMapping("/discounts")
    Collection<Discounts> staffs() {
        return this.discountsRepository.findAll();
    }

    @Autowired
    DiscountsRepository discountsRepository;
}
