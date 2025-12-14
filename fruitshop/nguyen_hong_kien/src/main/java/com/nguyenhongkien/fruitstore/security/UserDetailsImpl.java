package com.nguyenhongkien.fruitstore.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.nguyenhongkien.fruitstore.entity.User;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    // ⭐ Build chuẩn – không tạo authority bên ngoài
    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(user);
    }

    // ⭐ Spring Security sẽ gọi hàm này để lấy quyền
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
    }

    // ⭐ RẤT QUAN TRỌNG: Spring Security dùng hàm này để nhận username
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // ⭐ Nếu cần lấy User thật trong CartService, có thể dùng:
    public User getUser() {
        return user;
    }
}
