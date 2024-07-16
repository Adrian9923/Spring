package softuni.services;

import jakarta.xml.bind.JAXBException;

import java.io.IOException;
import java.math.BigDecimal;

public interface ProductService {
    void registerProducts() throws IOException;
    void registerXmlProducts() throws JAXBException, IOException;
    void exportProductsInRangeWithoutBuyerToJson(BigDecimal minPrice, BigDecimal maxPrice, String filePath) throws IOException;
}
