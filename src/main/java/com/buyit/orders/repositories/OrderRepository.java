package com.buyit.orders.repositories;

import com.buyit.orders.entities.OrderEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {
    List<OrderEntity> findByCustomerId(int customerId);
    List<OrderEntity> findAll();
}
