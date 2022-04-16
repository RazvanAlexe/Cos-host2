package com.buyit.cartItem.repositories;

import com.buyit.cartItem.entities.CartItemEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CartItemRepositoryTest {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Test
    void findByCartIdValidTest() {
        List<CartItemEntity> cartItemEntityListExpected = new ArrayList<>();
        List<CartItemEntity> cartItemEntityListActual = this.cartItemRepository.findByCartId(2);
        assertEquals(cartItemEntityListExpected, cartItemEntityListActual);
    }

    @Test
    void findByCartIdInvalidTest() {
        List<CartItemEntity> cartItemEntityListExpected = new ArrayList<>();
        List<CartItemEntity> cartItemEntityListActual = this.cartItemRepository.findByCartId(1);
        assertEquals(cartItemEntityListExpected, cartItemEntityListActual);
    }

    @Test
    void saveTest() {
        CartItemEntity cartItemEntityExpected = new CartItemEntity(1, 1, 10, 1, 1);
        CartItemEntity cartItemEntityActual = this.cartItemRepository.save(cartItemEntityExpected);
        assertEquals(cartItemEntityExpected, cartItemEntityActual);
    }
}