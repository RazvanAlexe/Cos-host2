package com.buyit.cart.DTOs;

import com.buyit.cartItem.DTOs.CartItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class CartDTO {
    private List<CartItemDTO> items;
    private double totalPrice;

    public CartDTO() {
        items = new ArrayList<>();
    }
}
