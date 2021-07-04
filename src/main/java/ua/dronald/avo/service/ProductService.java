package ua.dronald.avo.service;

import org.springframework.stereotype.Service;
import ua.dronald.avo.entity.Product;
import ua.dronald.avo.model.ProductModel;
import ua.dronald.avo.repository.CategoryRepository;
import ua.dronald.avo.repository.ProductRepository;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public void save(ProductModel productModel) throws IOException {
        productRepository.save(Product.builder()
                .name(productModel.getName())
                .description(productModel.getDescription())
                .price(productModel.getPrice())
                .image(Base64.getEncoder().encodeToString(productModel.getImage().getBytes()))
                .category(categoryRepository.findById(productModel.getCategoryId()).get())
                .time(Timestamp.from(Instant.now()))
                .build()
        );
    }

    public List<ProductModel> getProductModelTop4ByOrderByTime() {
        return productRepository.findTop4ByOrderByTime().stream().map((mapper) -> ProductModel.builder()
            .name(mapper.getName())
            .id(mapper.getId())
            .price(mapper.getPrice())
            .categoryId(mapper.getCategory().getId())
            .description(mapper.getDescription())
            .strImage(mapper.getImage())
            .image(null)
            .build()).collect(Collectors.toList());
    }

}
