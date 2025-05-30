package kz.kasssen.my_project.services;

import kz.kasssen.my_project.DTOs.ReviewResponseDTO;
import kz.kasssen.my_project.entity.Product;
import kz.kasssen.my_project.entity.Review;
import kz.kasssen.my_project.entity.User;
import kz.kasssen.my_project.repository.ProductRepository;
import kz.kasssen.my_project.repository.ReviewRepository;
import kz.kasssen.my_project.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public void addReview(String username, Long productId, String content, int rating) {
        User user = userRepository.findByUsername(username);
        Product product = productRepository.findById(productId).orElseThrow();

        Review review = new Review();

        review.setContent(content);
        review.setRating(rating);
        review.setCreatedAt(LocalDateTime.now());
        review.setProduct(product);
        review.setUser(user);

        reviewRepository.save(review);
    }

    public List<ReviewResponseDTO> getProductReviewById(Long productId) {
        List<Review> reviews = reviewRepository.findByProductId(productId);

        return reviews.stream().map(review -> {
            User user = userRepository.findByUsername(review.getUser().getUsername());
            return new ReviewResponseDTO(
                    user.getUsername(),
                    review.getRating(),
                    review.getContent(),
                    review.getCreatedAt().toString()
            );
        }).collect(Collectors.toList());
    }
}
