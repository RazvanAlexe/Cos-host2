package com.buyit.cart.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "carts")
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CartEntity {
    @Id
    private int id;
    private double totalPrice;
}
