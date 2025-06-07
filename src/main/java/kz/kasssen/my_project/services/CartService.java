package kz.kasssen.my_project.services;

import kz.kasssen.my_project.DTOs.CartItemDTO;
import kz.kasssen.my_project.entity.CartItem;
import kz.kasssen.my_project.entity.Product;
import kz.kasssen.my_project.entity.User;
import kz.kasssen.my_project.repository.CartItemRepository;
import kz.kasssen.my_project.repository.ProductRepository;
import kz.kasssen.my_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public void addToCart(String username, Long productId, Integer quantity, String selectedMemory, Double price) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem cartItem = cartItemRepository.findByUserAndProductIdAndSelectedMemory(user, productId, selectedMemory);

        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setSelectedMemory(selectedMemory);
            cartItem.setPrice(price);
            cartItem.setQuantity(quantity);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }

        cartItemRepository.save(cartItem);
    }


    public List<CartItemDTO> getUserCart(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<CartItem> cartItems = cartItemRepository.findCartByUserId(user.getId());

        return cartItems.stream()
                .map(CartItemDTO::new)
                .toList();
    }

    public void updateCartItemQuantity(String username, Long cartItemId, Integer newQuantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new RuntimeException("Item not found"));
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

        if (!cartItem.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not allowed to modify this item");
        }

        if (newQuantity <= 0) {
            throw new RuntimeException("Quantity must be greater than 0");
        }

        cartItem.setQuantity(newQuantity);
        cartItemRepository.save(cartItem);
    }


    public void removeCartItem(String username, Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!cartItem.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not allowed to delete this item");
        }

        cartItemRepository.delete(cartItem);
    }

}
