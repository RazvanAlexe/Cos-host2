package com.buyit.orders.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateOrderDTO {
    private int cartId;
    private int customerId;
    private int shippingAddressId;
    private int billingAddressId;
}
