package ua.dronald.avo.controller;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.dronald.avo.entity.Product;
import ua.dronald.avo.model.ProductModel;
import ua.dronald.avo.repository.ProductRepository;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class IndexController {

    private final ProductRepository productRepository;

    public IndexController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public ModelAndView index(@RequestParam(defaultValue = "false") boolean openedLogin,
                              @RequestParam(defaultValue = "false") boolean wrongPassword) {
        ModelAndView modelAndView = new ModelAndView("index");

        int count = (int) productRepository.count();
        List<Product> productList = productRepository.findAll();
        productList.sort(Comparator.comparing(Product::getTime));
        List<ProductModel> productModels = productList.subList(0, Math.min(count, 4)).stream().map(ProductModel::createByProduct).collect(Collectors.toList());

        if (productList.size() > 0) modelAndView.addObject("newProducts", productModels);

        if(openedLogin)
            modelAndView.addObject("openedLogin", true);
        if(wrongPassword)
            modelAndView.addObject("wrongPassword", true);
        return modelAndView;
    }


}
