package k23cnt3.ngdDay06lab.controller;

import k23cnt3.ngdDay06lab.entity.ngdAuthor;
import k23cnt3.ngdDay06lab.entity.ngdBook;
import k23cnt3.ngdDay06lab.service.NgdAuthorService;
import k23cnt3.ngdDay06lab.service.NgdBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ngdbooks")
public class NgdBookController {

    @Autowired
    private NgdBookService ngdBookService;

    @Autowired
    private NgdAuthorService ngdAuthorService;

    // Danh sách sách
    @GetMapping
    public String getNgdBooks(Model model) {
        model.addAttribute("ngdBooks", ngdBookService.getAllNgdBooks());
        return "ngdbooks/ngd-book-list";
    }

    // Form thêm sách
    @GetMapping("/new")
    public String showCreateFormNgdBook(Model model) {
        model.addAttribute("ngdBook", new ngdBook());
        model.addAttribute("ngdAuthors", ngdAuthorService.getAllNgdAuthors());
        return "ngdbooks/ngd-book-form";
    }

    // Lưu sách mới
    @PostMapping("/new")
    public String createNgdBook(
            @ModelAttribute ngdBook ngdBook,
            @RequestParam(required = false) List<Long> ngdAuthorIds,
            @RequestParam("imageBook") MultipartFile imageFile
    ) {
        // Xử lý tác giả
        List<ngdAuthor> authors = new ArrayList<>();
        if (ngdAuthorIds != null) {
            authors = ngdAuthorService.findNgdAuthorById(ngdAuthorIds);
        }
        ngdBook.setNgdAuthors(authors);

        // Xử lý ảnh
        if (!imageFile.isEmpty()) {
            try {
                String uploadDir = "src/main/resources/static/uploads/";
                File folder = new File(uploadDir);
                if (!folder.exists()) folder.mkdirs();
                String fileName = imageFile.getOriginalFilename();
                imageFile.transferTo(new File(uploadDir + fileName));
                ngdBook.setNgdImgUrl("/uploads/" + fileName); // URL hiển thị trong Thymeleaf
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ngdBookService.saveNgdBook(ngdBook);
        return "redirect:/ngdbooks";
    }

    // Form sửa sách
    @GetMapping("/edit/{id}")
    public String showEditFormNgdBook(@PathVariable("id") Long ngdId, Model model) {
        ngdBook book = ngdBookService.getNgdBookById(ngdId);
        if (book == null) {
            return "redirect:/ngdbooks";
        }
        model.addAttribute("ngdBook", book);
        model.addAttribute("ngdAuthors", ngdAuthorService.getAllNgdAuthors());
        return "ngdbooks/ngd-book-form";
    }

    // Lưu sửa sách
    @PostMapping("/edit/{id}")
    public String updateNgdBook(
            @PathVariable("id") Long ngdId,
            @ModelAttribute ngdBook ngdBook,
            @RequestParam(required = false) List<Long> ngdAuthorIds,
            @RequestParam("imageBook") MultipartFile imageFile
    ) {
        ngdBook existingBook = ngdBookService.getNgdBookById(ngdId);
        if (existingBook == null) {
            return "redirect:/ngdbooks";
        }

        // Cập nhật thông tin
        existingBook.setNgdCode(ngdBook.getNgdCode());
        existingBook.setNgdName(ngdBook.getNgdName());
        existingBook.setNgdDescription(ngdBook.getNgdDescription());
        existingBook.setNgdPrice(ngdBook.getNgdPrice());
        existingBook.setNgdQuantity(ngdBook.getNgdQuantity());
        existingBook.setNgdActive(ngdBook.getNgdActive());

        // Cập nhật tác giả
        List<ngdAuthor> authors = new ArrayList<>();
        if (ngdAuthorIds != null) {
            authors = ngdAuthorService.findNgdAuthorById(ngdAuthorIds);
        }
        existingBook.setNgdAuthors(authors);

        // Cập nhật ảnh nếu có
        if (!imageFile.isEmpty()) {
            try {
                String uploadDir = "src/main/resources/static/uploads/";
                File folder = new File(uploadDir);
                if (!folder.exists()) folder.mkdirs();
                String fileName = imageFile.getOriginalFilename();
                imageFile.transferTo(new File(uploadDir + fileName));
                existingBook.setNgdImgUrl("/uploads/" + fileName); // URL hiển thị trong Thymeleaf
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ngdBookService.saveNgdBook(existingBook);
        return "redirect:/ngdbooks";
    }

    // Xóa sách
    @GetMapping("/delete/{id}")
    public String deleteNgdBook(@PathVariable("id") Long ngdId) {
        ngdBookService.deleteNgdBook(ngdId);
        return "redirect:/ngdbooks";
    }
}
