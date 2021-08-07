package com.example.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@JsonIdentityInfo(scope = User.class, generator= ObjectIdGenerators.PropertyGenerator.class, property="id")

@Table(name="users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    @Column(length = 1024)
    private String username;

    //    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;



    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    /* fetcType = LAZY - whenever we use the relationship between shoppingcart and users, then to fetch the related entites
    * fetcht only when we need shopping
    * One to many relationship because for every first order there is new shopping cart for the user
    * If is One to One, there is only one shopping cart and we have to play with that*/
    @JsonIgnore
    private List<ShoppingCart> shoppingCarts;



    @ManyToMany(fetch = FetchType.EAGER) // eager because we want to initialize
    /* no need of @JoinTable as the previous example */
    private Set<Role> roles = new HashSet<>();


//    @OneToOne(cascade = CascadeType.ALL) // CascadeType.ALL - =if change occurs in the User entity, it means that the change
//    /* will ocurs also in the Adress Entity, (if we delete user --> we also delete adress) */
//    @JoinColumn(name = "address_id", referencedColumnName = "id") // this is the foreignt key
//    private Address address;


    @OneToOne(mappedBy = "user")
    private Address address;






    // One to one relationship with user and shopping cart //

//    @OneToOne(fetch = FetchType.LAZY,
//            cascade =  CascadeType.ALL,
//            mappedBy = "user")
//    @JsonManagedReference
//    //@JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
//    private ShoppingCart shoppingCart;


    @OneToMany(fetch = FetchType.LAZY)
    private List<Order> orders;

    public User(String username, String email,@NotNull String password, List<ShoppingCart> shoppingCarts, Set<Role> roles, Address address) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.shoppingCarts = shoppingCarts;
        this.roles = roles;
        this.address = address;
    }

    public User() {

    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
