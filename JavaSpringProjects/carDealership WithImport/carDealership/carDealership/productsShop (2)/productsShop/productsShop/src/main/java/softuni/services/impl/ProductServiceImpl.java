package softuni.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.dto.ProductDto;
import softuni.models.Category;
import softuni.models.Product;
import softuni.models.User;
import softuni.repositories.CategoryRepository;
import softuni.repositories.ProductRepository;
import softuni.repositories.UserRepository;
import softuni.services.ProductService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {
    private final String PRODUCTS_FILE_PATH = "C:\\Users\\Legion\\Desktop\\productsShop\\productsShop\\src\\main\\resources\\static\\files\\json\\products.json";
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(Gson gson, ModelMapper modelMapper, ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    private String readInformationFromFile() throws IOException {
        return Files.readString(Path.of(PRODUCTS_FILE_PATH));
    }

    @Override
    public void registerProducts() throws IOException {
        ProductDto[] products = this.gson
                .fromJson(this.readInformationFromFile(), ProductDto[].class);
        List<User> users = userRepository.findAll();
        List<Category> categories = categoryRepository.findAll();
        Random random = new Random();

        for (ProductDto productDto : products) {
            Product product = this.modelMapper.map(productDto, Product.class);

            int numOfCategories = Math.min(random.nextInt(5), categories.size() - 1);
            Set<Category> productCategories = new HashSet<>();
            for (int i = 0; i < numOfCategories; i++) {
                int randomIndex = random.nextInt(categories.size());
                Category randomCategory = categories.get(randomIndex);
                productCategories.add(randomCategory);
            }
            product.setCategories(productCategories);

            int buyerIndex = random.nextInt(users.size());
            int sellerIndex = random.nextInt(users.size());
            User buyer = users.get(buyerIndex);
            User seller = users.get(sellerIndex);
            product.setBuyer(buyer);
            product.setSeller(seller);
            this.productRepository.saveAndFlush(product);
        }

    }
}
