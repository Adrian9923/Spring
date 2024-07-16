package softuni;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.services.CategoryService;
import softuni.services.ProductService;
import softuni.services.UserService;

import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;

    public ConsoleRunner(UserService userService, ProductService productService, CategoryService categoryService) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
    }


    @Override
    public void run(String... args) throws Exception {
      /*  userService.registerUsers();
        productService.registerProducts();
        categoryService.registerCategories();

       */
       /* userService.registerXmlUsers();
        productService.registerXmlProducts();
        categoryService.registerXmlCategories();

        */
        BigDecimal minPrice = new BigDecimal("500");
        BigDecimal maxPrice = new BigDecimal("1000");
        String filePath = "src\\main\\resources\\static\\files\\ceva.json";

        productService.exportProductsInRangeWithoutBuyerToJson(minPrice, maxPrice, filePath);


    }
}
