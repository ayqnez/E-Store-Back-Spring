package kz.kasssen.my_project.controllers;

import kz.kasssen.my_project.entity.Product;
import kz.kasssen.my_project.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Product>> findByCategory(@RequestParam(required = false) String category) {
        if (category != null) {
            List<Product> products = productService.findByCategory(category);
            return ResponseEntity.ok(products);
        }
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @PostMapping()
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        productService.create(product);
        return ResponseEntity.ok("Product added");
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> findById(@PathVariable("productId") Long productId) {
        if (productId == null) {
            return ResponseEntity.notFound().build();
        }
        Product product = productService.findById(productId);
        return ResponseEntity.ok(product);
    }

}
