package softuni.services;

import jakarta.xml.bind.JAXBException;

import java.io.IOException;

public interface UserService {
    void registerUsers() throws IOException;
    void registerXmlUsers() throws JAXBException, IOException;
}
