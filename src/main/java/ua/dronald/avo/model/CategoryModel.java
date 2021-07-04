package ua.dronald.avo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.dronald.avo.entity.Category;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CategoryModel {

    private String name;

    private Long id;

    public CategoryModel(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
