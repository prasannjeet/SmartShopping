package org.cart.query.service.controller;

import org.cart.domain.service.dao.CartDao;
import org.cart.query.service.service.CartQueryService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/carts", produces = "application/json")
@ResponseBody
public class Controller {

    private CartQueryService cartQueryService;

    public Controller(CartQueryService cartQueryService) {
        this.cartQueryService = cartQueryService;
    }

    @GetMapping
    public List<CartDao> findAll() {
        return this.cartQueryService.findAll();
    }

    @GetMapping("/{userId}")
    public CartDao findByUserId(@PathVariable @NotBlank String userId) {
        return this.cartQueryService.findByUserId(userId);
    }
}
