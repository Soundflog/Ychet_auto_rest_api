package com.example.demo.Repositories;

import com.example.demo.Entities.Authorizations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public
interface AuthorizationsRepository extends JpaRepository<Authorizations, Long> {
    Collection<Authorizations> findAuthorizationsByLogin(String Login);

    @Override
    Optional<Authorizations> findById(Long id);
}
