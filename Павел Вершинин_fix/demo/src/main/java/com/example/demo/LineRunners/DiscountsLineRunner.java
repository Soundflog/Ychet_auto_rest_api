package com.example.demo.LineRunners;

import com.example.demo.Entities.Discounts;
import com.example.demo.Repositories.DiscountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DiscountsLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        for(Discounts d : this.discountsRepository.findAll())
            System.out.println(d.toString());
    }
    @Autowired
    DiscountsRepository discountsRepository;
}
