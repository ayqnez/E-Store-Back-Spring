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

    private String imageUrl; // основное изображение

    private String largeImageUrl; // маленькие изображения

    private Integer stock;

    private String screenSize;      // размер экрана
    private String cpu;             // процессор
    private Integer numberOfCores;  // количество ядер
    private String mainCamera;      // основная камера
    private String frontCamera;     // фронтальная камера
    private Integer batteryCapacity; // ёмкость батареи (mAh)

    @ElementCollection
    private List<String> memory; // доступные варианты памяти (128GB, 256GB, ...)
}
