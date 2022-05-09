package com.buyit.billing.address.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "billing_address")
@Data
public class BillingAddressEntity {
    @Id
    private Integer id;
    private String city;
    private String address;
}
