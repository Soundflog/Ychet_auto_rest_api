package com.example.demo.RestControllers;

import com.example.demo.Entities.Autos;
import com.example.demo.Repositories.AutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class AutosRestController {
    @RequestMapping("/autos")
    Collection<Autos> autos() {
        return this.autosRepository.findAll();
    }


    @Autowired
    AutosRepository autosRepository;
}
