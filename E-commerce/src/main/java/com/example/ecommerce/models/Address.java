package com.example.ecommerce.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@JsonIdentityInfo(scope = Address.class, generator= ObjectIdGenerators.PropertyGenerator.class, property="id")

@Table(name="addresses")
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotBlank
//    private String street;

    @NotBlank
    private String city;


//    @OneToOne(mappedBy = "address") // this is the owning side Adress is owner
//    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;


    @NotBlank
    private String addressName;

    @NotBlank
    private String state;

    @NotBlank
    private String country;

    @NotNull
    private Integer zipCode;

    @NotBlank
    private String phone;


    public Address(String addressName, String city, String state, String country, Integer zipCode, String phone) {
        this.addressName = addressName;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
        this.phone = phone;
    }


    public Address() {

    }
}
