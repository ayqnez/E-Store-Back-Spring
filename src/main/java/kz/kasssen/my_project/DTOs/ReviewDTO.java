package kz.kasssen.my_project.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewDTO {
    private String content;
    private Integer rating;
}
