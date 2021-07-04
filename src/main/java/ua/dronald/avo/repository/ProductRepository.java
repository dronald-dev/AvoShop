package ua.dronald.avo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.dronald.avo.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findTop4ByOrderByTime();
}
