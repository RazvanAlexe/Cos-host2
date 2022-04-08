package com.buyit.orders.DTOs;

import com.buyit.billing.address.DTOs.BillingAddressDTO;
import com.buyit.cart.DTOs.CartDTO;
import com.buyit.shipping.address.DTOs.ShippingAddressDTO;
import com.buyit.customer.DTOs.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private CustomerDTO userDTO;
    private CartDTO cartDTO;
    private ShippingAddressDTO shippingAddressDTO;
    private BillingAddressDTO billingAddressDTO;
}
