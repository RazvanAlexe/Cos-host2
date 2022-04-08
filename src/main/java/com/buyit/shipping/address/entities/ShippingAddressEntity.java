package com.buyit.shipping.address.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shipping_addresses")
@Data
public class ShippingAddressEntity {
    @Id
    private String id;
    private String address;
}
