package ua.dronald.avo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.dronald.avo.service.CategoryService;
import ua.dronald.avo.service.ProductService;

@Controller
public class IndexController {

    private final ProductService productService;

    private final CategoryService categoryService;

    public IndexController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public ModelAndView index(@RequestParam(defaultValue = "false") boolean openedLogin,
                              @RequestParam(defaultValue = "false") boolean wrongPassword) {
        ModelAndView modelAndView = new ModelAndView("index");

        modelAndView.addObject("newProducts", productService.getProductModelTop4ByOrderByTime());
        modelAndView.addObject("openedLogin", openedLogin);
        modelAndView.addObject("wrongPassword", wrongPassword);
        modelAndView.addObject("categories", categoryService.getAllCategories());

        return modelAndView;
    }


}
