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
}
