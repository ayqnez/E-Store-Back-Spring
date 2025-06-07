package kz.kasssen.my_project.DTOs;

import kz.kasssen.my_project.entity.CartItem;
import kz.kasssen.my_project.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDTO {
    private Long id;
    private Product product;
    private Integer quantity;
    private String selectedMemory;
    private Double price;

    public CartItemDTO(CartItem cartItem) {
        this.id = cartItem.getId();
        this.product = cartItem.getProduct();
        this.quantity = cartItem.getQuantity();
        this.price = cartItem.getPrice();
        this.selectedMemory = cartItem.getSelectedMemory();
    }
}
