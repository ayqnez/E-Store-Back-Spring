package kz.kasssen.my_project.DTOs;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ReviewResponseDTO {
    private String username;
    private Integer rating;
    private String content;
    private String createdAt;
}
