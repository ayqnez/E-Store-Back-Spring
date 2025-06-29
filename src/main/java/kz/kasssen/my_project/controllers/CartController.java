package kz.kasssen.my_project.controllers;

import kz.kasssen.my_project.DTOs.CartItemDTO;
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
                                       @RequestParam String selectedMemory,
                                       @RequestParam Double price,
                                       Authentication auth) {
        cartService.addToCart(auth.getName(), productId, quantity, selectedMemory, price);
        return ResponseEntity.ok("Product Added");
    }

    @GetMapping
    public ResponseEntity<List<CartItemDTO>> getCart(Authentication auth) {
        return ResponseEntity.ok(cartService.getUserCart(auth.getName()));
    }

    @PatchMapping("/{cartItemId}")
    public ResponseEntity<?> updateCartItemQuantity(Authentication auth,
                                                    @PathVariable Long cartItemId,
                                                    @RequestParam Integer newQuantity) {
        cartService.updateCartItemQuantity(auth.getName(), cartItemId, newQuantity);
        return ResponseEntity.ok("Product Updated");
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<?> deleteCart(Authentication auth, @PathVariable Long cartItemId) {
        cartService.removeCartItem(auth.getName(), cartItemId);
        return ResponseEntity.ok("Product Removed");
    }
}