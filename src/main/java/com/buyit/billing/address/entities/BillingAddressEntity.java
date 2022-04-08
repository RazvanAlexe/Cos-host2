package com.buyit.billing.address.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "billing_addresses")
@Data
public class BillingAddressEntity {
    @Id
    private String id;
    private String address;
}
