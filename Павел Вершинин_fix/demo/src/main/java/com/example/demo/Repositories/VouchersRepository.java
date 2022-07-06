package com.example.demo.Repositories;

import com.example.demo.Entities.Staffs;
import com.example.demo.Entities.Vouchers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Optional;
@Repository
public interface VouchersRepository extends JpaRepository<Vouchers, Long> {
    Collection<Vouchers> findVouchersByIndate(Timestamp Indate);

    @Override
    Optional<Vouchers> findById(Long id);
}
