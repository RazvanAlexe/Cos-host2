package com.buyit.cart.repositories;

import com.buyit.cart.entities.CartEntity;
import com.buyit.orders.repositories.repositories.CartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CartRepositoryTest {
    @Autowired
    private CartRepository cartRepository;

    @Test
    public void saveValidTest() {
        CartEntity cartEntityExpected = new CartEntity();
        cartEntityExpected.setId(1);
        cartEntityExpected.setTotalPrice(0);

        CartEntity cartEntityResult = this.cartRepository.save(cartEntityExpected);

        assertEquals(cartEntityExpected, cartEntityResult);
    }

    @Test
    public void findAllValidTest() {
        List<CartEntity> cartEntityListExpected = new ArrayList<>();
        cartEntityListExpected.add(new CartEntity(1, 0));

        List<CartEntity> cartEntityListActual = this.cartRepository.findAll();

        assertEquals(cartEntityListExpected, cartEntityListActual);

    }

    @Test
    public void findAllInvalidTest() {
        List<CartEntity> cartEntityListExpected = new ArrayList<>();

        List<CartEntity> cartEntityListActual = this.cartRepository.findAll();

        assertEquals(cartEntityListExpected, cartEntityListActual);
    }

    @Test
    public void findByIdValidTest() {
        CartEntity cartEntityExpected = new CartEntity(1, 0);
        Optional<CartEntity> cartEntityActual = this.cartRepository.findById(1);
        if (cartEntityActual.isPresent())
            assertEquals(cartEntityExpected, cartEntityActual.get());
        else
            assertEquals(cartEntityExpected, null);
    }

    @Test
    public void findByIdInvalidTest() {
        CartEntity cartEntityExpected = new CartEntity(9, 0);
        Optional<CartEntity> cartEntityActual = this.cartRepository.findById(9);
        if (cartEntityActual.isPresent())
            assertEquals(cartEntityExpected, cartEntityActual.get());
        else
            assertEquals(cartEntityExpected, null);
    }
}