package softuni.services;

import jakarta.xml.bind.JAXBException;

import java.io.IOException;

public interface CustomerService {
    void registerCustomers() throws IOException;
    void registerXmlCustomers() throws JAXBException, IOException;
}
