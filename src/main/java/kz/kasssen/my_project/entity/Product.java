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

    private Boolean isNewArrival;
    private Boolean isBestseller;
    private Boolean isFeatured;

    // Phones
    private String screenSize;
    private String screenType;
    private String screenResolution;
    private Integer screenRefreshRate;
    private Integer pixelDensity;

    private String cpu;
    private Integer numberOfCores;
    private Integer RAM;

    private String mainCamera;
    private String frontCamera;

    private Integer batteryCapacity;
    private Boolean wirelessCharging;
    private Boolean fastCharging;

    @ElementCollection
    private List<String> memory;


    // === Apple Watch / Smartwatch-specific fields ===
    private String caseSize;
    private String strapMaterial;
    private Boolean hasCellular;
    private Boolean waterResistant;
    private Boolean gpsEnabled;


    // === Наушники/Headphones-specific fields ===
    private String headphoneType; // "In-ear", "On-ear", "Over-ear" - тип конструкции
    private String connectionType; // "Wired", "Wireless", "Bluetooth" - способ подключения
    private Boolean noiseCancellation; // наличие активного шумоподавления (true/false)
    private String waterproofRating; // степень защиты (IPX4, IPX7 и т.д.)
    private String microphone; // "Built-in", "Detachable", "None" - наличие микрофона

    private Integer batteryLife;
}
