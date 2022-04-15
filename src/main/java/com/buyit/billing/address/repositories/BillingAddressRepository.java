package com.buyit.billing.address.repositories;

import com.buyit.billing.address.entities.BillingAddressEntity;
import org.springframework.data.repository.CrudRepository;

public interface BillingAddressRepository extends CrudRepository<BillingAddressEntity, Integer> {
}
