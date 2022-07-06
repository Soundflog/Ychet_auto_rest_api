package com.example.demo.LineRunners;

import com.example.demo.Entities.Vouchers;
import com.example.demo.Repositories.VouchersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class VouchersLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        for(Vouchers v : this.vouchersRepository.findAll())
            System.out.println(v.toString());
    }
    @Autowired
    VouchersRepository vouchersRepository;
}
