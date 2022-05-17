package com.buyit.payment.Controllers;

import com.buyit.orders.DTOs.OrderDTO;
import com.buyit.payment.Entities.ChargeRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Controller
public class CheckoutController {
    @Value("${stripe.public.key}")
    String stripePublicKey;


    @PostMapping("cart/payment/checkout")
    @CrossOrigin
    @Operation(
            summary = "Checkout page",
            description = "Show the payment result",
            tags = {"checkout cart"},
            responses = {
                    @ApiResponse(
                            description = "Page loaded",
                            responseCode = "200",
                            content = @Content(mediaType = "webpage/html")
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public String checkout(@RequestBody OrderDTO orderDetails, Model model) {
        model.addAttribute("amount", orderDetails.getCartDTO().getTotalPrice()); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.EUR);
        return "checkout";
    }
}
