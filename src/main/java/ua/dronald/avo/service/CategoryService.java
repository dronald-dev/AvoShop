package ua.dronald.avo.service;

import org.springframework.stereotype.Service;
import ua.dronald.avo.model.CategoryModel;
import ua.dronald.avo.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryModel> getAllCategories() {
        return categoryRepository.findAll().stream().map((mapper) -> CategoryModel.builder()
                .id(mapper.getId())
                .name(mapper.getName())
                .build()).collect(Collectors.toList());
    }

    public Optional<CategoryModel> getById(Long id) {
        return categoryRepository.findById(id).map(CategoryModel::new);
    }

}
