package kz.kasssen.my_project.services;

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

    public void addToCart(String username, Long productId, Integer quantity) {
        User user = userRepository.findByUsername(username);
        Product product = productRepository.findById(productId).orElseThrow();
        CartItem cartItem = cartItemRepository.findByUserAndProductId(user, productId);

        if (cartItem == null) {
            cartItem = new CartItem();
        }

        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);

        cartItemRepository.save(cartItem);
    }

    public List<Product> getUserCart(String username) {
        User user = userRepository.findByUsername(username);
        return cartItemRepository.findCartByUserId(user.getId())
                .stream()
                .map(CartItem::getProduct)
                .toList();
    }
}
