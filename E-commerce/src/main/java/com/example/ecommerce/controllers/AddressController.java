package com.example.ecommerce.controllers;


import com.example.ecommerce.models.Address;
import com.example.ecommerce.services.AddressService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/{userId}/create-address")
    public void addAddress(@Valid @RequestBody Address address, @PathVariable Long userId) throws ResourceNotFoundException {
        address.setUser(addressService.getUserById(userId));
        addressService.addAddress(address);
    }

    @PutMapping("/{addressId}/update-address")
    public ResponseEntity<Address> updateAddress(@PathVariable Long addressId, @Valid @RequestBody Address addressDetails) throws ResourceNotFoundException {
        return addressService.updateAddress(addressId, addressDetails);
    }

    @GetMapping("/{userId}")
    public Address getAddressByUserId(@PathVariable(value = "userId") Long userId) throws ResourceNotFoundException {
        return addressService.getAddressByUserId(userId);
    }





}
