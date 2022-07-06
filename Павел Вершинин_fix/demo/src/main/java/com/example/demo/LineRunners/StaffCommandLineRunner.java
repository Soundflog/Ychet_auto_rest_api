package com.example.demo.LineRunners;

import com.example.demo.Entities.Staffs;
import com.example.demo.Repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StaffCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        for(Staffs s : this.staffRepository.findAll())
            System.out.println(s.toString());
    }
    @Autowired
    StaffRepository staffRepository;
}
