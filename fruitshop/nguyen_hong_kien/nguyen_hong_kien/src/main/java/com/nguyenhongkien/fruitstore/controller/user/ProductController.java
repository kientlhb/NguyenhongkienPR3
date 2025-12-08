package com.nguyenhongkien.fruitstore.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.nguyenhongkien.fruitstore.entity.Product;
import com.nguyenhongkien.fruitstore.entity.Category;
import com.nguyenhongkien.fruitstore.service.ProductService;
import com.nguyenhongkien.fruitstore.service.CategoryService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    // ============================
    // 1. Danh sách sản phẩm (Mặc định)
    // ============================
    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productService.findAll();
        List<Product> featuredProducts = productService.findFeaturedProducts();
        List<Product> promotions = productService.findPromotionProducts();
        List<Product> newProducts = productService.findNewProducts();
        List<Category> categories = categoryService.findAll();

        model.addAttribute("products", products);
        model.addAttribute("featuredProducts", featuredProducts);
        model.addAttribute("promotions", promotions);
        model.addAttribute("newProducts", newProducts);
        model.addAttribute("categories", categories);
        model.addAttribute("selectedCategory", "Tất cả");

        return "user/products";
    }

    // ============================
    // 2. Chi tiết sản phẩm
    // ============================
    @GetMapping("/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        if (product == null) {
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        return "user/product-detail";
    }

    // ============================
    // 3. Tìm kiếm sản phẩm
    // URL: /products/search?keyword=abc
    // ============================
    @GetMapping("/search")
    public String searchProducts(@RequestParam("keyword") String keyword, Model model) {
        List<Product> products = productService.search(keyword);
        List<Product> featuredProducts = productService.findFeaturedProducts();
        List<Product> promotions = productService.findPromotionProducts();
        List<Product> newProducts = productService.findNewProducts();
        List<Category> categories = categoryService.findAll();

        model.addAttribute("products", products);
        model.addAttribute("featuredProducts", featuredProducts);
        model.addAttribute("promotions", promotions);
        model.addAttribute("newProducts", newProducts);
        model.addAttribute("categories", categories);
        model.addAttribute("keyword", keyword);
        model.addAttribute("selectedCategory", "Kết quả tìm kiếm");

        return "user/products";
    }

    // ============================
    // 4. Lọc theo danh mục
    // URL: /products/category/{id}
    // ============================
    @GetMapping("/category/{id}")
    public String filterByCategory(@PathVariable Long id, Model model) {
        List<Product> products;
        if (id == 0) { // 0 = Tất cả
            products = productService.findAll();
        } else {
            products = productService.findByCategory(id);
        }

        List<Product> featuredProducts = productService.findFeaturedProducts();
        List<Product> promotions = productService.findPromotionProducts();
        List<Product> newProducts = productService.findNewProducts();
        List<Category> categories = categoryService.findAll();

        Category selectedCategory = categoryService.findById(id);
        String categoryName = (id == 0) ? "Tất cả" : (selectedCategory != null ? selectedCategory.getName() : "Danh mục không tồn tại");

        model.addAttribute("products", products);
        model.addAttribute("featuredProducts", featuredProducts);
        model.addAttribute("promotions", promotions);
        model.addAttribute("newProducts", newProducts);
        model.addAttribute("categories", categories);
        model.addAttribute("selectedCategory", categoryName);

        return "user/products";
    }
}
