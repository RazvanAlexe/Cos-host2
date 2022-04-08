package com.buyit.cartItem.repositories;

import com.buyit.cartItem.entities.CartItemEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartItemRepository extends CrudRepository<CartItemEntity, String> {

    List<CartItemEntity> findByCartId(String cartId);
}
