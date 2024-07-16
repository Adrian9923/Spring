package softuni.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.dto.CategoryDto;
import softuni.models.Category;
import softuni.repositories.CategoryRepository;
import softuni.services.CategoryService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final String CATEGORIES_FILE_PATH = "C:\\Users\\Legion\\Desktop\\productsShop\\productsShop\\src\\main\\resources\\static\\files\\json\\categories.json";
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(Gson gson, ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    private String readInformationFromFile() throws IOException {
        return Files.readString(Path.of(CATEGORIES_FILE_PATH));
    }

    @Override
    public void registerCategories() throws IOException {

        CategoryDto[] categories = this.gson
                .fromJson(this.readInformationFromFile(), CategoryDto[].class);

        for (CategoryDto categoryDto : categories) {
            Category category = this.modelMapper.map(categoryDto, Category.class);
            this.categoryRepository.saveAndFlush(category);
        }

    }
}
