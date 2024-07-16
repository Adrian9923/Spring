package softuni.services;

import jakarta.xml.bind.JAXBException;

import java.io.IOException;

public interface PartService {
    void registerParts() throws IOException;
    void registerXmlParts() throws JAXBException, IOException;
}
