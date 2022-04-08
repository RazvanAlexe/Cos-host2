package com.buyit.customer.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "customers")
@Data
public class CustomerEntity {
    @Id
    private String id;
    private String name;
}
