package com.buyit.cartItem.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cart_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemEntity {
    @Id
    private String id;
    private int quantity;
    private double price;
    private String cartId;
    private int productId;
}
