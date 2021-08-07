package com.example.ecommerce.repository;

import com.example.ecommerce.models.Role;
import com.example.ecommerce.models.enumerations.ERoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName (ERoles name);
}
