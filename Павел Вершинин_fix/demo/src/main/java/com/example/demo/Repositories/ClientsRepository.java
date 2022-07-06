package com.example.demo.Repositories;

import com.example.demo.Entities.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ClientsRepository extends JpaRepository<Clients, Long> {
    Collection<Clients> findClientsByLastname(String Lastname);

    @Override
    Optional<Clients> findById(Long aLong);
}
