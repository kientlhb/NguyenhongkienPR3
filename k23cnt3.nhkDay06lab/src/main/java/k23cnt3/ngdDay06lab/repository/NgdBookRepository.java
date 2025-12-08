package k23cnt3.ngdDay06lab.repository;

import k23cnt3.ngdDay06lab.entity.ngdBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NgdBookRepository extends JpaRepository<ngdBook, Long> {
}

