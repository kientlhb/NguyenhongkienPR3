package com.nguyenhongkien.fruitstore.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import  com.nguyenhongkien.fruitstore.entity.User;
import  com.nguyenhongkien.fruitstore.service.CartService;
import  com.nguyenhongkien.fruitstore.service.UserService;

@Controller
@RequestMapping("/cart")

public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String viewCart(Model model) {
        User user = userService.getCurrentUser();

        // ⭐ Kiểm tra null khi xem giỏ hàng ⭐
        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("items", cartService.getItems(user));
        model.addAttribute("total", cartService.getTotal(user));
        return "user/cart";
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam Long productId,
                             @RequestParam Integer quantity) {
        User user = userService.getCurrentUser();

        // ⭐ SỬA LỖI: Nếu User là null, chuyển hướng đến đăng nhập ⭐
        if (user == null) {
            return "redirect:/login";
        }

        cartService.addProduct(user, productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateItem(@RequestParam Long cartItemId,
                             @RequestParam Integer quantity) {
        // ... (cần thêm logic kiểm tra user ở đây để bảo mật) ...
        cartService.updateItem(cartItemId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeItem(@RequestParam Long cartItemId) {
        // ... (cần thêm logic kiểm tra user ở đây để bảo mật) ...
        cartService.removeItem(cartItemId);
        return "redirect:/cart";
    }

        @PostMapping("/buyNow")
        public String buyNow(@RequestParam Long productId,
                @RequestParam Integer quantity) {
            User user = userService.getCurrentUser();

            // 1. Kiểm tra đăng nhập
            if (user == null) {
                return "redirect:/login";
            }

            // 2. Thêm sản phẩm vào giỏ hàng (Tái sử dụng service)
            cartService.addProduct(user, productId, quantity);

            // 3. Chuyển hướng người dùng thẳng đến trang Giỏ hàng
            return "redirect:/cart";
        }
    }