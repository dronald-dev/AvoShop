package ua.dronald.avo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.dronald.avo.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
