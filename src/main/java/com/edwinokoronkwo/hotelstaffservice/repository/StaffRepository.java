package com.edwinokoronkwo.hotelstaffservice.repository;


import com.edwinokoronkwo.hotelstaffservice.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
}
