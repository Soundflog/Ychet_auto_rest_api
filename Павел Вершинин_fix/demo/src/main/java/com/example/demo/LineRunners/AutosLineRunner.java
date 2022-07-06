package com.example.demo.LineRunners;

import com.example.demo.Entities.Autos;
import com.example.demo.Repositories.AutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AutosLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        for(Autos a : this.autosRepository.findAll())
            System.out.println(a.toString());
    }
    @Autowired
    AutosRepository autosRepository;
}
