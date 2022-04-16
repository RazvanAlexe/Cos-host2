package com.buyit.product.repositories;

import com.buyit.orders.entities.OrderEntity;
import com.buyit.product.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {

}
