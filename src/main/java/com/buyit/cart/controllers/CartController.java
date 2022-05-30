package com.buyit.cart.controllers;

import com.buyit.auth.AuthService;
import com.buyit.cart.DTOs.CartDTO;
import com.buyit.cart.entities.CartEntity;
import com.buyit.cart.services.CartService;
import com.buyit.cart.DTOs.ItemToCartDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/cart")
public class CartController {

    private final CartService cartService;
    private final AuthService authService;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    public CartController(CartService cartService,
                          AuthService authService) {
        this.cartService = cartService;
        this.authService = authService;
    }

    @PostMapping("/addUserCart")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Adds new cart",
            description = "Creates a new cart and adds it to the list of carts. Sets total price to 0. ",
            tags = {"AddCart"},
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CartEntity.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public CartEntity addNewUserCart() {
        String username = authService.getUsernameFromRequestTokenHeader(request.getHeader("Authorization"));
        System.out.println(username);
        return this.cartService.addNewUserCart(username);
    }

    @GetMapping("/mine")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Gets products from an user's cart",
            description = "Searches for a cart identified by the userId. It returns all the products from the specified cart",
            tags = {"GetCartByID"},
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CartDTO.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public CartDTO getMyCart() {
        String username = authService.getUsernameFromRequestTokenHeader(request.getHeader("Authorization"));
        System.out.println(username);
        return cartService.getCartByUsername(username);
    }

    @PatchMapping("/addProduct")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Adds a product to cart",
            description = "The product will be added to the cart if the product isn't already in the cart. Otherwise, the quantity of the product from the cart will be increased by one.",
            tags = {"AddProduct"},
            responses = {
                    @ApiResponse(
                            description = "Updated",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemToCartDTO.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public CartDTO addProductInCart(@RequestBody ItemToCartDTO itemToCartDTO) {
        String username = authService.getUsernameFromRequestTokenHeader(request.getHeader("Authorization"));
        System.out.println(username);
        return cartService.addProductInCart(itemToCartDTO.getProductId(), username);
    }

    @PatchMapping("/removeProduct")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Removes a product from the cart",
            description = "The product will be deleted from the cart if the quantity of the product from the cart is 1. Otherwise, the quantity of the product will be decremented by one.",
            tags = {"RemoveProduct"},
            responses = {
                    @ApiResponse(
                            description = "Updated",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemToCartDTO.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public CartDTO removeProductFromCart(@RequestBody ItemToCartDTO itemToCartDTO) {
        String username = authService.getUsernameFromRequestTokenHeader(request.getHeader("Authorization"));
        System.out.println(username);
        System.out.println(itemToCartDTO);
        return cartService.removeProductFromCart(itemToCartDTO.getProductId(), username);
    }
}
