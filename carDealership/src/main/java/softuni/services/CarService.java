package softuni.services;

import jakarta.xml.bind.JAXBException;

import java.io.IOException;

public interface CarService {
    void registerCars() throws IOException;
    void registerXmlCars() throws JAXBException, IOException;
}
