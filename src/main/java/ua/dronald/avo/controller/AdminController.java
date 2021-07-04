package ua.dronald.avo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.dronald.avo.entity.Category;
import ua.dronald.avo.model.CategoryModel;
import ua.dronald.avo.model.ProductModel;
import ua.dronald.avo.repository.CategoryRepository;
import ua.dronald.avo.service.CategoryService;
import ua.dronald.avo.service.ProductService;

import java.io.IOException;

@Controller
public class AdminController {

    private final CategoryService categoryService;

    private final ProductService productService;

    private final CategoryRepository categoryRepository;

    public AdminController(CategoryService categoryService, ProductService productService, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/admin")
    public ModelAndView adminPanel() {
        return new ModelAndView("admin")
                .addObject("product", new ProductModel())
                .addObject("categories", categoryService.getAllCategories())
                .addObject("category", new CategoryModel());
    }

    // TODO: Rewrite this code, make checking in another class
    @PostMapping("/product/add")
    public ModelAndView addProduct(@ModelAttribute("product") ProductModel productModel) {
        if(categoryService.getById(productModel.getCategoryId()).isEmpty())
            return new ModelAndView("/");
        try {
            productService.save(productModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/admin");
    }

    @PostMapping("/category/add")
    public ModelAndView addCategory(@ModelAttribute("category") CategoryModel categoryModel) {
        categoryRepository.save(Category.builder()
            .name(categoryModel.getName())
            .build()
        );
        return new ModelAndView("redirect:/admin");
    }

    @PostMapping("/category/remove")
    public ModelAndView removeCategory(@ModelAttribute("product") CategoryModel categoryModel) {
        categoryRepository.deleteById(categoryModel.getId());
        return new ModelAndView("redirect:/admin");
    }

}
