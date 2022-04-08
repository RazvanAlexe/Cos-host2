package com.buyit.cart.services;

import com.buyit.cart.DTOs.CartDTO;
import com.buyit.cart.entities.CartEntity;
import com.buyit.cart.repositories.CartRepository;
import com.buyit.cartItem.DTOs.CartItemDTO;
import com.buyit.cartItem.entities.CartItemEntity;
import com.buyit.cartItem.repositories.CartItemRepository;
import com.buyit.product.entities.ProductEntity;
import com.buyit.product.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class CartServiceTest {
    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private CartItemRepository cartItemRepository;

    @MockBean
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

    @Test
    void addNewCartValidTest() {
        List<CartEntity> cartEntityListMock = new ArrayList<>();
        cartEntityListMock.add(new CartEntity("cid1", 0));
        int size = cartEntityListMock.size() + 1;

        CartEntity cartEntityExpected = new CartEntity();
        cartEntityExpected.setId("cid" + size);
        cartEntityExpected.setTotalPrice(0);

        Mockito.when(cartRepository.save(cartEntityExpected)).thenReturn(cartEntityExpected);

        Mockito.when(cartRepository.findAll()).thenReturn(cartEntityListMock);

        CartEntity cartEntityActual = this.cartService.addNewCart();
        assertEquals(cartEntityExpected, cartEntityActual);
    }

    @Test
    void addNewCartInvalidTest() {
        List<CartEntity> cartEntityListMock = new ArrayList<>();
        cartEntityListMock.add(new CartEntity("cid1", 0));
        int size = cartEntityListMock.size() + 1;

        CartEntity cartEntityExpected = new CartEntity();
        cartEntityExpected.setId("cid" + size);
        cartEntityExpected.setTotalPrice(10);

        Mockito.when(cartRepository.save(cartEntityExpected)).thenReturn(cartEntityExpected);

        Mockito.when(cartRepository.findAll()).thenReturn(cartEntityListMock);

        CartEntity cartEntityActual = this.cartService.addNewCart();
        assertEquals(cartEntityExpected, cartEntityActual);
    }

    @Test
    void getCartByIdValidTest() {
        CartEntity cartEntityMock = new CartEntity("cid1", 0);
        List<CartItemEntity> cartItemEntityListMock = new ArrayList<>();
        ProductEntity productEntityMock = new ProductEntity();

        CartDTO cartDTOExpected = new CartDTO(new ArrayList<>(), 0);
        Mockito.when(cartRepository.findById("cid1")).thenReturn(Optional.of(cartEntityMock));
        Mockito.when(cartItemRepository.findByCartId("cid1")).thenReturn(cartItemEntityListMock);
        Mockito.when(productRepository.findById("cid1")).thenReturn(Optional.of(productEntityMock));

        CartDTO cartDTOActual = this.cartService.getCartById("cid1");

        assertEquals(cartDTOExpected, cartDTOActual);

    }

    @Test
    void getCartByIdInvalidTest() {
        CartEntity cartEntityMock = new CartEntity("cid1", 10);
        List<CartItemEntity> cartItemEntityListMock = new ArrayList<>();
        ProductEntity productEntityMock = new ProductEntity();

        CartDTO cartDTOExpected = new CartDTO(new ArrayList<>(), 0);
        Mockito.when(cartRepository.findById("cid1")).thenReturn(Optional.of(cartEntityMock));
        Mockito.when(cartItemRepository.findByCartId("cid1")).thenReturn(cartItemEntityListMock);
        Mockito.when(productRepository.findById("cid1")).thenReturn(Optional.of(productEntityMock));

        CartDTO cartDTOActual = this.cartService.getCartById("cid1");

        assertEquals(cartDTOExpected, cartDTOActual);

    }

    @Test
    void addProductInCartTest() {
        CartEntity cartEntityMock = new CartEntity();
        cartEntityMock.setId("cid1");
        cartEntityMock.setTotalPrice(10);

        List<CartItemEntity> itemEntityListMock = new ArrayList<>();

        CartItemEntity cartItemEntityMock = new CartItemEntity("ciid1", 1, 10, "cid1", "pid1");

        itemEntityListMock.add(cartItemEntityMock);

        ProductEntity productEntityMock = new ProductEntity("pid1", "name1", 10);

        List<CartItemDTO> cartItemDTOListExpected = new ArrayList<>();
        cartItemDTOListExpected.add(new CartItemDTO(2, 20, productEntityMock));

        Mockito.when(cartRepository.findById("cid1")).thenReturn(Optional.of(cartEntityMock));
        Mockito.when(cartItemRepository.findByCartId("cid1")).thenReturn(itemEntityListMock);
        Mockito.when(cartItemRepository.save(cartItemEntityMock)).thenReturn(cartItemEntityMock);
        Mockito.when(cartRepository.save(cartEntityMock)).thenReturn(cartEntityMock);
        Mockito.when(productRepository.findById("pid1")).thenReturn(Optional.of(productEntityMock));

        CartDTO cartDTOExpected = new CartDTO(cartItemDTOListExpected, 20);

        CartDTO cartDTOActual = this.cartService.addProductInCart("pid1", "cid1");

        assertEquals(cartDTOExpected, cartDTOActual);
    }

    @Test
    void removeProductFromCartTest() {
        CartEntity cartEntityMock = new CartEntity();
        cartEntityMock.setId("cid1");
        cartEntityMock.setTotalPrice(20);

        List<CartItemEntity> itemEntityListMock = new ArrayList<>();

        CartItemEntity cartItemEntityMock = new CartItemEntity("ciid1", 2, 20, "cid1", "pid1");

        itemEntityListMock.add(cartItemEntityMock);

        ProductEntity productEntityMock = new ProductEntity("pid1", "name1", 10);

        List<CartItemDTO> cartItemDTOListExpected = new ArrayList<>();
        cartItemDTOListExpected.add(new CartItemDTO(1, 10, productEntityMock));

        Mockito.when(cartRepository.findById("cid1")).thenReturn(Optional.of(cartEntityMock));
        Mockito.when(cartItemRepository.findByCartId("cid1")).thenReturn(itemEntityListMock);
        Mockito.when(cartItemRepository.save(cartItemEntityMock)).thenReturn(cartItemEntityMock);
        Mockito.when(cartRepository.save(cartEntityMock)).thenReturn(cartEntityMock);
        Mockito.when(productRepository.findById("pid1")).thenReturn(Optional.of(productEntityMock));

        CartDTO cartDTOExpected = new CartDTO(cartItemDTOListExpected, 10);

        CartDTO cartDTOActual = this.cartService.removeProductFromCart("pid1", "cid1");

        assertEquals(cartDTOExpected, cartDTOActual);
    }
}