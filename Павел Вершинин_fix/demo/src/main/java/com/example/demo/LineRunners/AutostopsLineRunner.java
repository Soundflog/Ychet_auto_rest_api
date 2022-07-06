package com.example.demo.LineRunners;

import com.example.demo.Entities.Autostops;
import com.example.demo.Repositories.AutostopsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AutostopsLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        for(Autostops a: this.autostopsRepository.findAll())
            System.out.println(a.toString());
    }
    @Autowired
    AutostopsRepository autostopsRepository;
}
