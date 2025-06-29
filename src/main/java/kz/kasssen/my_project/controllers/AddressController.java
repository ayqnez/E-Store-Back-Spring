package kz.kasssen.my_project.controllers;

import kz.kasssen.my_project.entity.Address;
import kz.kasssen.my_project.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/all")
    public ResponseEntity<List<Address>> getAddresses(Authentication auth) {
        return ResponseEntity.ok(addressService.getUserAddress(auth.getName()));
    }

    @PostMapping("/add-address")
    public ResponseEntity<?> addAddress(Authentication auth, @RequestBody Address address) {
        addressService.addAddressToUser(auth.getName(), address);
        return ResponseEntity.ok("Address saved");
    }
}
