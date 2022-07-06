package com.example.demo.Repositories;

import com.example.demo.Entities.Discounts;
import com.example.demo.Entities.Staffs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface DiscountsRepository extends JpaRepository<Discounts, Long> {
    Collection<Discounts> findDiscountsBySale(Integer Sale);

    @Override
    Optional<Discounts> findById(Long id);
}
