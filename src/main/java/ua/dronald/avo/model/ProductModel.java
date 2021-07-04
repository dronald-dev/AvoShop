package ua.dronald.avo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ua.dronald.avo.entity.Product;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {

    private String name;

    private String description;

    private BigDecimal price;


    private Long categoryId;

    private MultipartFile image;

    private String strImage;

    public static ProductModel createByProduct(Product product) {
        return ProductModel.builder()
                .name(product.getName())
                .price(product.getPrice())
                .categoryId(product.getCategory().getId())
                .description(product.getDescription())
                .strImage(product.getImage())
                .image(null)
                .build();
    }

}
