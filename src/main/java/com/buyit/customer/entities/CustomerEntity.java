package com.buyit.customer.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "client")
@Data
public class CustomerEntity {
    @Id
    private int id;
    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
}
