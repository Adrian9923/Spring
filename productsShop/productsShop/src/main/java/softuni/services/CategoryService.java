package softuni.services;

import jakarta.xml.bind.JAXBException;

import java.io.IOException;

public interface CategoryService {
    void registerCategories() throws IOException;
    void registerXmlCategories() throws JAXBException, IOException;
    void insertJsonFile() throws IOException;
}
