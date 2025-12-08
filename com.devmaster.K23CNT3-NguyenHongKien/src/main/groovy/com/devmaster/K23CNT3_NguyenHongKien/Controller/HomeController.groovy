package com.devmaster.K23CNT3_NguyenHongKien

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {

    @GetMapping("/")
    String index() {
        return "index"
    }

    @GetMapping("/profile")
    String profile(Model model) {
        def profiles = [
                [name: "Nguyễn Hồng Kiên", nickName: "KienHK", email: "kien@example.com", website: "https://devmaster.edu.vn"]
        ]
        model.addAttribute("DevmasterProfile", profiles)
        return "profile"
    }
}
