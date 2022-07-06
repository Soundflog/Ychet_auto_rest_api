package com.example.demo.RestControllers;

import com.example.demo.Entities.Staffs;
import com.example.demo.Repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class StaffRestController {
    @RequestMapping("/staffs")
    Collection<Staffs> staffs() {
        return this.staffRepository.findAll();
    }

    @Autowired
    StaffRepository staffRepository;
}
