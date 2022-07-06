package com.example.demo.Repositories;

import com.example.demo.Entities.Contracts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface ContractsRepository extends JpaRepository<Contracts, Long> {
    Collection<Contracts> findContractsByConclusionDate(Timestamp ConclusionDate);

    @Override
    Optional<Contracts> findById(Long id);
}
