package kz.kasssen.my_project.services;

import kz.kasssen.my_project.entity.Favorite;
import kz.kasssen.my_project.entity.Product;
import kz.kasssen.my_project.entity.User;
import kz.kasssen.my_project.repository.FavoriteRepository;
import kz.kasssen.my_project.repository.ProductRepository;
import kz.kasssen.my_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public void toggleFavorite(String username, Long productId, boolean isFavorite) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User Not Found"));
        Product product = productRepository.findById(productId).orElseThrow();

        Favorite favorite = favoriteRepository.findByUserAndProduct(user, product);
        if (favorite == null) {
            favorite = new Favorite();
        }

        favorite.setUser(user);
        favorite.setProduct(product);
        favorite.setFavorite(isFavorite);

        favoriteRepository.save(favorite);
    }

    public List<Product> getUserFavorites(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User Not Found"));
        return favoriteRepository.findAllByUserAndIsFavoriteTrue(user)
                .stream()
                .map(Favorite::getProduct)
                .toList();
    }
}
