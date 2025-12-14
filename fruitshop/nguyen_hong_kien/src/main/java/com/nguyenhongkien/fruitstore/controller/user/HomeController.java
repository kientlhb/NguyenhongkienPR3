package com.nguyenhongkien.fruitstore.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.nguyenhongkien.fruitstore.entity.Product;
import com.nguyenhongkien.fruitstore.entity.User;
import com.nguyenhongkien.fruitstore.repository.ProductRepository;
import com.nguyenhongkien.fruitstore.repository.UserRepository;

import java.util.List;

@Controller
public class HomeController {

    private final ProductRepository productRepository;

    public HomeController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal User user) {
        List<Product> featuredProducts = productRepository.findByFeaturedTrue();
        model.addAttribute("featuredProducts", featuredProducts);
        model.addAttribute("user", user); // để hiển thị tên user nếu đã login
        return "user/home";
    }
}