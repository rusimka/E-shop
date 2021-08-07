package com.example.ecommerce.repository;

import com.example.ecommerce.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    Address findAddressByUserId(Long userId);

}
