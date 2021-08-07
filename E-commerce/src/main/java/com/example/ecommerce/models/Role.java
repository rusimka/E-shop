package com.example.ecommerce.models;


import com.example.ecommerce.models.enumerations.ERoles;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="roles")
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private ERoles name;

    public Role(ERoles name) {
        this.name = name;
    }

    public Role() {

    }
}
