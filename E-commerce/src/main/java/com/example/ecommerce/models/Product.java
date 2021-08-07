package com.example.ecommerce.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="products")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String productName;

    @Column
    @NotNull
    private Float price;

    @Column
    @NotNull
    private Integer quantity;

    @Column(name="image")
    @Lob
    @NotNull
    private String imageBase64;

    @Column()
    private String description;


    @ManyToMany(mappedBy = "products",fetch = FetchType.EAGER) // this means that products table is inversed side,  the owner of the relationship.
    @JsonIgnore
    List<ShoppingCart> shoppingCarts;
    /* mappedBy for ManyToMany the both sides can be owning side
    * The inverse or the referencing side simply maps to the owning side - this is the owning side */

    public Product(  String productName,  Float price,  Integer quantity,String description) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }


    public Product() {
    }
}
