package com.buyit.shipping.address.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shipping_address")
@Data
public class ShippingAddressEntity {
    @Id
    private int id;
    private String city;
    private String Address;
}
