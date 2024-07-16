package softuni.services;

import jakarta.xml.bind.JAXBException;

import java.io.IOException;

public interface PersonService {
    void registerPeople() throws IOException;
    void registerXmlPeople() throws JAXBException, IOException;
}
