package kz.kasssen.my_project.controllers;

import kz.kasssen.my_project.DTOs.UserDTO;
import kz.kasssen.my_project.entity.User;
import kz.kasssen.my_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final UserRepository userRepository;

    @GetMapping()
    public ResponseEntity<UserDTO> getProfile(Authentication auth) {
        String username = auth.getName();
        User user = userRepository.findByUsername(username);

        UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getRole());
        return ResponseEntity.ok(userDTO);
    }
}
