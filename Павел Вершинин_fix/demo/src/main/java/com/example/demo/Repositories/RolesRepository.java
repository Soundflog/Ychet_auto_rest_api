package com.example.demo.Repositories;

import com.example.demo.Entities.Authorizations;
import com.example.demo.Entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
    Collection<Roles> findRolesByNamerole(String Namerole);

    @Override
    Optional<Roles> findById(Long id);
}
