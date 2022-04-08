package com.buyit.orders.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateOrderDTO {
    private String cartId;
    private String customerId;
    private String shippingAddressId;
    private String billingAddressId;
}
