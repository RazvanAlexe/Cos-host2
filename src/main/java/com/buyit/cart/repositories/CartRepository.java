package com.buyit.cart.repositories;

import com.buyit.cart.entities.CartEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartRepository extends CrudRepository<CartEntity, Integer> {
    List<CartEntity> findAll();

}
