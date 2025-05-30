package kz.kasssen.my_project.controllers;

import kz.kasssen.my_project.entity.Product;
import kz.kasssen.my_project.services.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/{productId}")
    public ResponseEntity<?> addFavorite(@PathVariable Long productId,
                                         @RequestParam boolean favorite,
                                         Authentication auth) {
        favoriteService.toggleFavorite(auth.getName(), productId, favorite);
        return ResponseEntity.ok("Favorite added");
    }   

    @GetMapping
    public ResponseEntity<List<Product>> getFavorites(Authentication auth) {
        return ResponseEntity.ok(favoriteService.getUserFavorites(auth.getName()));
    }

}
