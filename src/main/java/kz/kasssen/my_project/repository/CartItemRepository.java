package kz.kasssen.my_project.repository;

import kz.kasssen.my_project.entity.CartItem;
import kz.kasssen.my_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByUserAndProductId(User user, Long productId);
    CartItem findByUserAndProductIdAndSelectedMemory(User user, Long productId, String selectedMemory);
    List<CartItem> findCartByUserId(Long userId);
}
