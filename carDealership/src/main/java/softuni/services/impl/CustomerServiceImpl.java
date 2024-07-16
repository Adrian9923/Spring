package softuni.services.impl;

import com.google.gson.Gson;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.dto.CustomerDto;
import softuni.dto.CustomerRootDto;
import softuni.dto.CustomerXmlDto;
import softuni.models.Customer;
import softuni.repositories.CustomerRepository;
import softuni.services.CustomerService;
import softuni.util.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final String CUSTOMER_SERVICE_PATH = "C:\\Users\\Legion\\Desktop\\carDealership\\carDealership\\src\\main\\resources\\static\\files\\json\\customers.json";
    private final String CUSTOMER_SERVICE_XML_PATH = "C:\\Users\\Legion\\Desktop\\carDealership\\carDealership\\src\\main\\resources\\static\\files\\xml\\customers.xml";
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(Gson gson, XmlParser xmlParser, ModelMapper modelMapper, CustomerRepository customerRepository) {
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
    }

    private String readInformationFromFile() throws IOException {
        return Files.readString(Path.of(CUSTOMER_SERVICE_PATH));
    }

    @Override
    public void registerCustomers() throws IOException {
        CustomerDto[] customers = this.gson
                .fromJson(this.readInformationFromFile(), CustomerDto[].class);

        for (CustomerDto customerDto : customers) {
            Customer customer = this.modelMapper.map(customerDto, Customer.class);
            customer.setBirthDate(customerDto.getBirthDate());
            this.customerRepository.saveAndFlush(customer);
        }

    }

    @Override
    public void registerXmlCustomers() throws JAXBException, IOException {
        CustomerRootDto customerRootDto = this.xmlParser
                .parseXml(CustomerRootDto.class, this.CUSTOMER_SERVICE_XML_PATH);

        for (CustomerXmlDto customerXmlDto : customerRootDto.getCustomers()) {
            Customer customer = this.modelMapper.map(customerXmlDto, Customer.class);
            this.customerRepository.saveAndFlush(customer);
        }

    }
}
