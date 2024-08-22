package com.synex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synex.domain.ChargeRequest;

@Controller
public class CheckoutController {

    private String stripePublicKey = "pk_test_51PNmQNFoEq7Q2rGLdvlxdglolE9eRcKIJMUpGmCtIPnP8Uc6579ol4X9I0Wc4DACQHLSudaLUtxzPKmayUCp2Uv200sq40P9HE";

    @RequestMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("amount", 50 * 100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.USD);
        return "checkout";
    }
}