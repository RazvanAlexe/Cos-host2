package com.buyit.cart.controllers;

import com.buyit.cart.DTOs.CartDTO;
import com.buyit.cart.DTOs.ItemToCartDTO;
import com.buyit.cart.entities.CartEntity;
import com.buyit.cart.services.CartService;
import com.buyit.cartItem.DTOs.CartItemDTO;
import com.buyit.product.entities.ProductEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CartController.class)
class CartControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @Test
    void addNewCartValidTest() throws Exception {
        CartEntity cartEntityExpected = new CartEntity();
        cartEntityExpected.setId(1);
        cartEntityExpected.setTotalPrice(0);

        when(cartService.addNewCart()).thenReturn(cartEntityExpected);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cart/add");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String stringResult = mvcResult.getResponse().getContentAsString();
        CartEntity cartEntityActual = new ObjectMapper().readValue(stringResult, CartEntity.class);

        assertEquals(cartEntityExpected, cartEntityActual);
    }

    @Test
    void addNewCartInvalidTest() throws Exception {
        CartEntity cartEntityExpected = new CartEntity();
        cartEntityExpected.setId(1);
        cartEntityExpected.setTotalPrice(0);

        when(cartService.addNewCart()).thenReturn(cartEntityExpected);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cartt/add");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String stringResult = mvcResult.getResponse().getContentAsString();
        CartEntity cartEntityActual = new ObjectMapper().readValue(stringResult, CartEntity.class);

        assertEquals(cartEntityExpected, cartEntityActual);
    }

    @Test
    void getCartByIdValidTest() throws Exception {
        CartDTO cartDTOExpected = new CartDTO(new ArrayList<>(), 0);

        when(cartService.getCartById(1)).thenReturn(cartDTOExpected);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cart/get/{id}", 1);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String stringResult = mvcResult.getResponse().getContentAsString();
        CartDTO cartDTOActual = new ObjectMapper().readValue(stringResult, CartDTO.class);

        assertEquals(cartDTOExpected, cartDTOActual);
    }

    @Test
    void getCartByIdInvalidTest() throws Exception {
        CartDTO cartDTOExpected = new CartDTO(new ArrayList<>(), 0);

        when(cartService.getCartById(1)).thenReturn(cartDTOExpected);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cart/gett/{id}", 1);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String stringResult = mvcResult.getResponse().getContentAsString();
        CartDTO cartDTOActual = new ObjectMapper().readValue(stringResult, CartDTO.class);

        assertEquals(cartDTOExpected, cartDTOActual);
    }

    @Test
    void addProductInCartTest() throws Exception {
        CartDTO cartDTOExpected = new CartDTO();
        cartDTOExpected.setTotalPrice(10);

        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setQuantity(1);
        cartItemDTO.setPrice(10);
        cartItemDTO.setProductEntity(new ProductEntity(1, "name1", 10, "", 0, 0, 0, 0, 0));

        List<CartItemDTO> cartItemDTOS = new ArrayList<>();
        cartItemDTOS.add(cartItemDTO);

        cartDTOExpected.setItems(cartItemDTOS);

        ItemToCartDTO itemToCartDTOMock = new ItemToCartDTO(1, 1);

        ObjectMapper objectMapper = new ObjectMapper();

        String jsonString = objectMapper.writeValueAsString(itemToCartDTOMock);

        when(cartService.addProductInCart(1,1)).thenReturn(cartDTOExpected);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cart/addProduct")
                .contentType(MediaType.APPLICATION_JSON).content(jsonString);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String stringResult = mvcResult.getResponse().getContentAsString();
        CartDTO cartDTOActual = objectMapper.readValue(stringResult, CartDTO.class);

        assertEquals(cartDTOExpected, cartDTOActual);
    }

    @Test
    void removeProductInCartTest() throws Exception {
        CartDTO cartDTOExpected = new CartDTO();
        cartDTOExpected.setItems(new ArrayList<>());
        cartDTOExpected.setTotalPrice(0);

        ItemToCartDTO itemToCartDTOMock = new ItemToCartDTO(1, 1);

        ObjectMapper objectMapper = new ObjectMapper();

        String jsonString = objectMapper.writeValueAsString(itemToCartDTOMock);

        when(cartService.removeProductFromCart(1,1)).thenReturn(cartDTOExpected);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/cart/removeProduct")
                .contentType(MediaType.APPLICATION_JSON).content(jsonString);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String stringResult = mvcResult.getResponse().getContentAsString();
        CartDTO cartDTOActual = objectMapper.readValue(stringResult, CartDTO.class);

        assertEquals(cartDTOExpected, cartDTOActual);
    }
}