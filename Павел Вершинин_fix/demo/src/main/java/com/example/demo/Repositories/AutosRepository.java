package com.example.demo.Repositories;

import com.example.demo.Entities.Autos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface AutosRepository extends JpaRepository<Autos, Long> {
    Collection<Autos> findAutosByAutoname(String Autoname);

    @Override
    Optional<Autos> findById(Long id);
}
