package ua.dronald.avo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {

    private String name;

    private String description;

    private BigDecimal price;

    private Long id;

    private Long categoryId;

    @Nullable
    private MultipartFile image;

    @Nullable
    private String strImage;

}
