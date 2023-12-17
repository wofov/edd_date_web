package com.edd.date.repository;

import com.edd.date.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    Admin findByAdminId(String adminId);
}
