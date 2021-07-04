package ua.dronald.avo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.util.StringUtils;
import ua.dronald.avo.entity.Category;
import ua.dronald.avo.model.CategoryModel;
import ua.dronald.avo.model.ProductModel;
import ua.dronald.avo.repository.CategoryRepository;
import ua.dronald.avo.repository.ProductRepository;
import ua.dronald.avo.service.ProductService;

import java.io.IOException;

@Controller
public class AdminController {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final ProductService productService;

    public AdminController(ProductRepository productRepository, CategoryRepository categoryRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productService = productService;
    }

    @GetMapping("/admin")
    public ModelAndView adminPanel() {
        return new ModelAndView("admin")
                .addObject("product", new ProductModel())
                .addObject("categories", categoryRepository.findAll())
                .addObject("category", new CategoryModel());
    }

    // TODO: Rewrite this code, make checking in another class
    @PostMapping("/product/add")
    public ModelAndView addProduct(@ModelAttribute("product") ProductModel productModel) {
        if(categoryRepository.findById(productModel.getCategoryId()).isEmpty())
            return new ModelAndView("/");
        if(StringUtils.cleanPath(productModel.getImage().getOriginalFilename()).contains(".."))
            return new ModelAndView("redirect:/admin");
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
