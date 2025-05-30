package kz.kasssen.my_project.DTOs;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String role; // можно сделать по умолчанию "USER"
}
