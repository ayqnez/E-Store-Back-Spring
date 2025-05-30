package kz.kasssen.my_project.controllers;

import kz.kasssen.my_project.DTOs.ReviewDTO;
import kz.kasssen.my_project.DTOs.ReviewResponseDTO;
import kz.kasssen.my_project.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/addReview/{productId}")
    public ResponseEntity<?> addReview(@PathVariable Long productId, Authentication auth, @RequestBody ReviewDTO reviewDTO) {
        reviewService.addReview(auth.getName(), productId, reviewDTO.getContent(), reviewDTO.getRating());
        return ResponseEntity.ok("Review added");
    }

    @GetMapping("/productReview/{productId}")
    public ResponseEntity<List<ReviewResponseDTO>> getReviews(@PathVariable Long productId) {
        return ResponseEntity.ok(reviewService.getProductReviewById(productId));
    }
}
