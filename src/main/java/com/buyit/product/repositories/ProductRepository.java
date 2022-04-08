package com.buyit.product.repositories;

import com.buyit.product.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, String> {

}
