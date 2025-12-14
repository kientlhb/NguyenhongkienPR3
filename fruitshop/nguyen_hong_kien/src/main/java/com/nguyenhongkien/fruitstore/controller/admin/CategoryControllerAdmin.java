    package com.nguyenhongkien.fruitstore.controller.admin;
    import com.nguyenhongkien.fruitstore.entity.Category;
    import com.nguyenhongkien.fruitstore.repository.CategoryRepository;

    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    import java.util.Optional;

    @Controller
    @RequestMapping("/admin/categories")
    public class CategoryControllerAdmin {

        private final CategoryRepository categoryRepository;

        public CategoryControllerAdmin(CategoryRepository categoryRepository) {
            this.categoryRepository = categoryRepository;
        }

        // Hiển thị danh sách danh mục
        @GetMapping
        public String listCategories(Model model) {
            List<Category> categories = categoryRepository.findAll();
            model.addAttribute("categories", categories);
            return "admin/categories/list";
        }

        // Form thêm danh mục mới
        @GetMapping("/add")
        public String showAddForm(Model model) {
            model.addAttribute("category", new Category());
            return "admin/categories/form";
        }

        // Lưu danh mục (thêm hoặc sửa)
        @PostMapping("/save")
        public String saveCategory(@ModelAttribute("category") Category category) {
            categoryRepository.save(category);
            return "redirect:/admin/categories";
        }

        // Form sửa danh mục
        @GetMapping("/edit/{id}")
        public String showEditForm(@PathVariable Long id, Model model) {
            Optional<Category> categoryOpt = categoryRepository.findById(id);
            if (categoryOpt.isPresent()) {
                model.addAttribute("category", categoryOpt.get());
                return "admin/categories/form";
            } else {
                return "redirect:/admin/categories";
            }
        }

        // Xóa danh mục
        @GetMapping("/delete/{id}")
        public String deleteCategory(@PathVariable Long id) {
            categoryRepository.deleteById(id);
            return "redirect:/admin/categories";
        }
    }
