package kz.kasssen.my_project.services;

import kz.kasssen.my_project.entity.Address;
import kz.kasssen.my_project.entity.User;
import kz.kasssen.my_project.repository.AddressRepository;
import kz.kasssen.my_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public List<Address> getUserAddress(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("User not found"));

        return addressRepository.findByUserId(user.getId());
    }

    public void addAddressToUser(String username, Address address) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new RuntimeException("User not found"));
        address.setUser(user);
        addressRepository.save(address);
    }
}
