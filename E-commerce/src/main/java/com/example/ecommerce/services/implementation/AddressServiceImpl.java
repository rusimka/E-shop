package com.example.ecommerce.services.implementation;

import com.example.ecommerce.models.Address;
import com.example.ecommerce.models.User;
import com.example.ecommerce.models.exceptions.UserNotFoundException;
import com.example.ecommerce.repository.AddressRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.services.AddressService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressServiceImpl(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addAddress(Address newAddress) {
        addressRepository.save(newAddress);


    }

    @Override
    public ResponseEntity<Address> updateAddress(Long addressId, Address addressDetails) throws ResourceNotFoundException {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id:" + addressId));
        address.setAddressName(addressDetails.getAddressName());
        address.setCity(addressDetails.getCity());
        address.setCountry(addressDetails.getCountry());
        address.setState(addressDetails.getState());
        address.setZipCode(addressDetails.getZipCode());
        address.setPhone(addressDetails.getPhone());

        final Address updatedAddress = addressRepository.save(address);
        return ResponseEntity.ok(updatedAddress);
    }

    @Override
    public Address getAddressByUserId(Long userId) throws ResourceNotFoundException {
        //System.out.println(this.addressRepository.findAddressByUserId(userId).getAddressName());
        return this.addressRepository.findAddressByUserId(userId);

    }

    @Override
    public User getUserById(Long id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found with id: " + id));
    }
}
