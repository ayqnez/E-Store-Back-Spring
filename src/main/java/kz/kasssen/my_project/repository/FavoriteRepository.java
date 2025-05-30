package kz.kasssen.my_project.repository;

import kz.kasssen.my_project.entity.Favorite;
import kz.kasssen.my_project.entity.Product;
import kz.kasssen.my_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Favorite findByUserAndProduct(User user, Product product);
    List<Favorite> findAllByUserAndIsFavoriteTrue(User user);
}
