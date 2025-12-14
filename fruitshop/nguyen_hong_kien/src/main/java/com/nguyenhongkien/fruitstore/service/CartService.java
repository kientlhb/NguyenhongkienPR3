package com.nguyenhongkien.fruitstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nguyenhongkien.fruitstore.entity.Cart;
import com.nguyenhongkien.fruitstore.entity.CartItem;
import com.nguyenhongkien.fruitstore.entity.Product;
import com.nguyenhongkien.fruitstore.entity.User;
import com.nguyenhongkien.fruitstore.repository.CartItemRepository;
import com.nguyenhongkien.fruitstore.repository.CartRepository;
import com.nguyenhongkien.fruitstore.repository.ProductRepository;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public Cart getCartByUser(User user) {
        Cart cart = cartRepository.findByUser(user);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }
        return cart;
    }

    public List<CartItem> getItems(User user) {
        return getCartByUser(user).getItems();
    }

    // ⭐ PHƯƠNG THỨC ĐÃ SỬA LỖI LƯU TRỮ CHÍNH
    public void addProduct(User user, Long productId, Integer quantity) {
        Cart cart = getCartByUser(user);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));

        CartItem existingItem = null;
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getId().equals(productId)) {
                existingItem = item;
                break;
            }
        }

        if (existingItem != null) {
            // Trường hợp 1: Sản phẩm đã có -> Cập nhật số lượng
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            // KHÔNG CẦN cartItemRepository.save(existingItem);
        } else {
            // Trường hợp 2: Sản phẩm mới -> Tạo CartItem mới và thêm vào Cart
            CartItem item = new CartItem(cart, product, quantity);
            cart.addItem(item);
            // KHÔNG CẦN cartItemRepository.save(item);
        }

        // ⭐ CHỈ LƯU CART CHÍNH: Spring sẽ tự động lưu CartItem nhờ CascadeType.ALL
        cartRepository.save(cart);
    }

    public void updateItem(Long cartItemId, Integer quantity) {
        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Item không tồn tại"));
        item.setQuantity(quantity);
        cartItemRepository.save(item);
    }

    public void removeItem(Long cartItemId) {
        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Item không tồn tại"));
        Cart cart = item.getCart();
        cart.removeItem(item);
        cartRepository.save(cart);
    }

    public double getTotal(User user) {
        double total = 0;
        for (CartItem item : getItems(user)) {
            // Giả định Product có phương thức getPrice()
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }

    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }

    public void clearCart(User user) {
        Cart cart = getCartByUser(user);
        cart.getItems().clear();
        cartRepository.save(cart);
    }
}