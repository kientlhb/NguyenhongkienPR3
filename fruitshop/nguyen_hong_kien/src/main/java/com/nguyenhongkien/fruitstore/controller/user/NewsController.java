package com.nguyenhongkien.fruitstore.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.nguyenhongkien.fruitstore.entity.News;
import com.nguyenhongkien.fruitstore.service.NewsService;

@Controller
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public String listNews(Model model) {
        model.addAttribute("newsList", newsService.getAllNews());
        // Trả về template: src/main/resources/templates/user/news/list.html
        return "user/news/list";
    }

    @GetMapping("/{id}")
    public String newsDetail(@PathVariable Long id, Model model) {
        News news = newsService.getNewsById(id);
        if (news == null) {
            return "redirect:/news";
        }
        // Trả về template: src/main/resources/templates/user/news/detail.html
        model.addAttribute("news", news);
        return "user/news/detail";
    }
}