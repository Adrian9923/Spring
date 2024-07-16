package softuni.services;

import jakarta.xml.bind.JAXBException;

import java.io.IOException;

public interface SupplierService {
    void registerSuppliers() throws IOException;
    void registerXmlSuppliers() throws JAXBException, IOException;
}
