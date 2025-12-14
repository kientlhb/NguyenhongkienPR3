package com.nguyenhongkien.fruitstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import com.nguyenhongkien.fruitstore.entity.Review; // ⭐ Entity Review
import com.nguyenhongkien.fruitstore.entity.User;
import com.nguyenhongkien.fruitstore.entity.Product;
import java.util.List;

@Repository
// QUẢN LÝ ENTITY REVIEW
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // ⭐ Phương thức cần thiết cho ReviewService
    List<Review> findByUser(User user);

    // ⭐ Phương thức cần thiết cho ReviewService
    List<Review> findByProduct(Product product);

    @Query("SELECT AVG(r.rating) FROM Review r")
    Double getAverageRating();
}