package kz.kasssen.my_project.services;

import kz.kasssen.my_project.entity.Product;
import kz.kasssen.my_project.repository.ProductRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public void create(Product product) {
        productRepository.save(product);
    }

    public Product findById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }
}
