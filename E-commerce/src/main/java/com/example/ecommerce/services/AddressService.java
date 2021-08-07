package com.example.ecommerce.services;


import com.example.ecommerce.models.Address;
import com.example.ecommerce.models.User;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface AddressService {

    public void addAddress(Address newAddress);

    public ResponseEntity<Address> updateAddress(Long addressId,
                                                 @Valid @RequestBody Address addressDetails)  throws ResourceNotFoundException;
    public Address getAddressByUserId(Long userId) throws ResourceNotFoundException;
    public User getUserById(Long id) throws ResourceNotFoundException;

}
