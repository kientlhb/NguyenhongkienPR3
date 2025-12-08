package com.nguyenhongkien.fruitstore.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.nguyenhongkien.fruitstore.entity.Role;
import com.nguyenhongkien.fruitstore.entity.User;
import com.nguyenhongkien.fruitstore.repository.UserRepository;

@Component
public class DataInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initAdmin() {
        // LƯU Ý: userRepository.findByUsername() trả về Optional<User>
        // Kiểm tra nếu admin3 chưa có thì tạo mới
        if (userRepository.findByUsername("admin3").isEmpty()) { // Sử dụng .isEmpty()
            User admin = new User();
            admin.setUsername("admin3");
            admin.setPassword(passwordEncoder.encode("123456")); // encode mật khẩu

            // LỖI 1 ĐÃ SỬA: Giả định bạn đã thêm trường 'fullName' vào User.java
            admin.setFullName("Admin Three");

            admin.setEmail("admin3@gmail.com");
            admin.setPhone("0123456789");

            // LỖI 2 ĐÃ SỬA: Giả định Role là Enum (có hằng số ADMIN)
            admin.setRole(Role.ADMIN);

            userRepository.save(admin);
            System.out.println(">>> Admin3 account created successfully!");
        }
    }
}