package softuni.services.impl;

import com.google.gson.Gson;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.dto.CarDto;
import softuni.dto.CarRootDto;
import softuni.dto.CarXmlDto;
import softuni.models.Car;
import softuni.repositories.CarRepository;
import softuni.services.CarService;
import softuni.util.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CarServiceImpl implements CarService {
    private final String CARS_FILE_PATH = "C:\\Users\\Legion\\Desktop\\carDealership\\carDealership\\src\\main\\resources\\static\\files\\json\\cars.json";
    private final String CARS_FILE_XML_PATH = "C:\\Users\\Legion\\Desktop\\carDealership\\carDealership\\src\\main\\resources\\static\\files\\xml\\cars.xml";
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final CarRepository carRepository;

    public CarServiceImpl(Gson gson, XmlParser xmlParser, ModelMapper modelMapper, CarRepository carRepository) {
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.carRepository = carRepository;
    }

    private String readInformationFromFile() throws IOException {
        return Files.readString(Path.of(CARS_FILE_PATH));
    }

    @Override
    public void registerCars() throws IOException {
        CarDto[] cars = this.gson
                .fromJson(this.readInformationFromFile(), CarDto[].class);

        for (CarDto carDto : cars) {
            Car car = this.modelMapper.map(carDto, Car.class);
            this.carRepository.saveAndFlush(car);
        }

    }

    @Override
    public void registerXmlCars() throws JAXBException, IOException {
        CarRootDto carRootDto = this.xmlParser
                .parseXml(CarRootDto.class, this.CARS_FILE_XML_PATH);

        for (CarXmlDto carXmlDto : carRootDto.getCars()) {
            Car car = this.modelMapper.map(carXmlDto, Car.class);
            this.carRepository.saveAndFlush(car);
        }

    }
}
