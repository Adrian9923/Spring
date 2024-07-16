package softuni.services;

import jakarta.xml.bind.JAXBException;

import java.io.IOException;

public interface SaleService {
    void registerSales() throws IOException;
    void registerXmlSales() throws JAXBException, IOException;
}
