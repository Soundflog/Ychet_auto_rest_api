package com.example.demo.RestControllers;

import com.example.demo.Entities.Staffs;
import com.example.demo.Entities.Vouchers;
import com.example.demo.Repositories.StaffRepository;
import com.example.demo.Repositories.VouchersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class VouchersRestController {
    @RequestMapping("/vouchers")
    Collection<Vouchers> vouchers() {
        return this.vouchersRepository.findAll();
    }

    @Autowired
    VouchersRepository vouchersRepository;
}
