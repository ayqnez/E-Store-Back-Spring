package kz.kasssen.my_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private String brand;
    private Double price;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String imageUrl;
    private String largeImageUrl;

    private Integer stock;

    private String screenSize;
    private String cpu;
    private Integer numberOfCores;
    private String mainCamera;
    private String frontCamera;
    private Integer batteryCapacity;

    @ElementCollection
    private List<String> memory;

    // === Apple Watch / Smartwatch-specific fields ===
    private String caseSize;
    private String strapMaterial;
    private Boolean hasCellular;
    private Boolean waterResistant;
    private Boolean gpsEnabled;
    private Integer batteryLife;
}
