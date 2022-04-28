package com.buyit.cartItem.DTOs;

import com.buyit.product.entities.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {
    private int quantity;
    private double price;
    private ProductEntity productEntity;

    public CartItemDTO(CartItemDTO cart){
        this.quantity = cart.quantity;
        this.price = cart.price;
        this.productEntity = cart.productEntity;
    }

    public void operate() {
        quantity++;
        price = price / (quantity - 1) * quantity;
    }
}
