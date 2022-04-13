package com.buyit.billing.address.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BillingAddressDTO {
    private String city;
    private String address;
}
