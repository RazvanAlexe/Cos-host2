package com.buyit.shipping.address.repositories;

import com.buyit.shipping.address.entities.ShippingAddressEntity;
import org.springframework.data.repository.CrudRepository;

public interface ShippingAddressRepository extends CrudRepository<ShippingAddressEntity, String> {
}
