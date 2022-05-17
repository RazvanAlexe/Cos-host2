package com.buyit.payment.Controllers;

import com.buyit.orders.entities.OrderEntity;
import com.buyit.payment.Entities.ChargeRequest;
import com.buyit.payment.StripeService.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.AuthenticationException;

@Controller
public class ChargeController {

    private final StripeService paymentsService;

    public ChargeController(StripeService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @PostMapping("cart/payment/charge")
    @CrossOrigin
    @Operation(
            summary = "Payment charge",
            description = "Will make a charge request and returns the result of the payment into a new view",
            tags = {"charge cart"},
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
    public String charge(ChargeRequest chargeRequest, Model model)
            throws StripeException, AuthenticationException {
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.EUR);
        Charge charge = paymentsService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        String result = "result";
        return result;
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        String result = "result";
        return result;
    }
}
