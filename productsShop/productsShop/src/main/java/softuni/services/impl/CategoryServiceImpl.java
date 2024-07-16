package softuni.services.impl;

import com.google.gson.Gson;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.dto.CategoryDto;
import softuni.dto.CategoryRootDto;
import softuni.dto.CategoryXmlDto;
import softuni.models.Category;
import softuni.repositories.CategoryRepository;
import softuni.services.CategoryService;
import softuni.util.XmlParser;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final String CATEGORIES_FILE_PATH = "C:\\Users\\Legion\\Desktop\\productsShop\\productsShop\\src\\main\\resources\\static\\files\\json\\categories.json";
    private final String CATEGORIES_FILE_XML_PATH = "C:\\Users\\Legion\\Desktop\\productsShop\\productsShop\\src\\main\\resources\\static\\files\\xml\\categories.xml";
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(Gson gson, XmlParser xmlParser, ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.gson = gson;
        this.xmlParser = xmlParser;
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

    @Override
    public void registerXmlCategories() throws JAXBException, IOException {
        CategoryRootDto categoryRootDto = this.xmlParser
                .parseXml(CategoryRootDto.class, this.CATEGORIES_FILE_XML_PATH);

        for (CategoryXmlDto categoryXmlDto : categoryRootDto.getCategories()) {
            Category category = this.modelMapper.map(categoryXmlDto, Category.class);
            this.categoryRepository.saveAndFlush(category);
        }

    }

    @Override
    public void insertJsonFile() throws IOException {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("Darius");
        String converted = gson.toJson(categoryDto);

        String cevaJsonFilePath = "src\\main\\resources\\static\\files\\ceva.json";

        try (FileWriter fileWriter = new FileWriter(cevaJsonFilePath)) {
            fileWriter.write(converted);
        }

    }
}
