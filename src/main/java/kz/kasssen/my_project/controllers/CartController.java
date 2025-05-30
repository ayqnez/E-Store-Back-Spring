package kz.kasssen.my_project.controllers;

import kz.kasssen.my_project.entity.Product;
import kz.kasssen.my_project.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/{productId}")
    public ResponseEntity<?> addToCart(@PathVariable Long productId,
                                       @RequestParam Integer quantity,
                                       Authentication auth) {
        cartService.addToCart(auth.getName(), productId, quantity);
        return ResponseEntity.ok("Product Added");
    }

    @GetMapping
    public ResponseEntity<List<Product>> getCart(Authentication auth) {
        return ResponseEntity.ok(cartService.getUserCart(auth.getName()));
    }
}