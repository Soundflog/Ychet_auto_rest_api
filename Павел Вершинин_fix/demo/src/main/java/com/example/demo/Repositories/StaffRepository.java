package com.example.demo.Repositories;

import com.example.demo.Entities.Staffs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
@Repository
public interface StaffRepository extends JpaRepository<Staffs, Long> {
    Collection<Staffs> findStaffsByDesription(String Desription);

    @Override
    Optional<Staffs> findById(Long id);
}
