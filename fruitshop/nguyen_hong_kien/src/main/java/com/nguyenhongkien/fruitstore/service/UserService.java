package com.nguyenhongkien.fruitstore.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.nguyenhongkien.fruitstore.entity.User;
import com.nguyenhongkien.fruitstore.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ⭐ ĐÃ SỬA LỖI: Trả về null nếu chưa đăng nhập thay vì ném Exception ⭐
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Nếu chưa xác thực hoặc là người dùng ẩn danh, trả về null.
        if (authentication == null || !authentication.isAuthenticated() ||
                authentication.getPrincipal().equals("anonymousUser")) {
            return null;
        }

        String username = authentication.getName(); // Lấy username từ Spring Security

        // Trả về User nếu tìm thấy, hoặc null nếu không tìm thấy (mặc dù hiếm khi xảy ra nếu đã xác thực)
        return userRepository.findByUsername(username)
                .orElse(null);
    }
}