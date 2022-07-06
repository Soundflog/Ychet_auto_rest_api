package com.example.demo.Repositories;

import com.example.demo.Entities.Autostops;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface AutostopsRepository extends JpaRepository<Autostops, Long> {
    Collection<Autostops> findAutostopsByName(String Name);

    @Override
    Optional<Autostops> findById(Long id);
}
