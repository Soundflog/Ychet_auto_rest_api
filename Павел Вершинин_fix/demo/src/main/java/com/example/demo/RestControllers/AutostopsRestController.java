package com.example.demo.RestControllers;

import com.example.demo.Entities.Autostops;
import com.example.demo.Repositories.AutostopsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class AutostopsRestController {
    @RequestMapping("/autostops")
    Collection<Autostops> autostops() {
        return this.autostopsRepository.findAll();
    }

    @Autowired
    AutostopsRepository autostopsRepository;
}
