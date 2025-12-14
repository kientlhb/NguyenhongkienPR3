package com.nguyenhongkien.fruitstore.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nguyenhongkien.fruitstore.entity.User;
import com.nguyenhongkien.fruitstore.entity.Role;
import com.nguyenhongkien.fruitstore.entity.Cart;
import com.nguyenhongkien.fruitstore.repository.UserRepository;
import com.nguyenhongkien.fruitstore.repository.CartRepository;


@Controller
@RequestMapping("/admin/user")
public class UserControllerAdmin {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ⭐ LIST USER
    @GetMapping
    public String list(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin/user/list";
    }

    // ⭐ FORM ADD
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", Role.values());
        return "admin/user/form";
    }

    // ⭐ SAVE (ADD + EDIT)
    @PostMapping("/save")
    public String save(@ModelAttribute User user) {

        if (user.getId() == null) {
            // ✅ USER MỚI → mã hóa password + tạo cart 1 lần
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            Cart cart = new Cart();
            user.setCart(cart);

        } else {
            // ✅ EDIT USER
            User oldUser = userRepository.findById(user.getId()).orElseThrow();

            // giữ cart cũ
            user.setCart(oldUser.getCart());

            // nếu không nhập password → giữ password cũ
            if (user.getPassword() == null || user.getPassword().isBlank()) {
                user.setPassword(oldUser.getPassword());
            } else {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
        }

        userRepository.save(user);
        return "redirect:/admin/user";
    }

    // ⭐ FORM EDIT
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).orElseThrow();
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "admin/user/form";
    }

    // ⭐ DELETE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/admin/user";
    }
}