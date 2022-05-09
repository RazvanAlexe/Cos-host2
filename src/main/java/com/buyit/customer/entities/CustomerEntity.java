package com.buyit.customer.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "client")
@Data
public class CustomerEntity {
    @Id
    @Column(name = "iduser")
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private Date birthday;
    @Column(nullable = true)
    private Integer cartId;
}
