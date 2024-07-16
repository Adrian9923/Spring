package softuni.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.dto.CustomerDto;
import softuni.models.Customer;
import softuni.repositories.CustomerRepository;
import softuni.services.CustomerService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final String CUSTOMER_SERVICE_PATH = "C:\\Users\\Legion\\Desktop\\carDealership\\carDealership\\src\\main\\resources\\static\\files\\json\\customers.json";
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(Gson gson, ModelMapper modelMapper, CustomerRepository customerRepository) {
        this.gson = gson;
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
}
