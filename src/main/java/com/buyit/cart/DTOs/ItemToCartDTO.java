package com.buyit.cart.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemToCartDTO {
    private String productId;
    private String cartId;
}
