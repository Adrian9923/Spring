package softuni.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.dto.SupplierDto;
import softuni.models.Supplier;
import softuni.repositories.SupplierRepository;
import softuni.services.SupplierService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final String SUPPLIER_FILE_PATH = "C:\\Users\\Legion\\Desktop\\carDealership\\carDealership\\src\\main\\resources\\static\\files\\json\\suppliers.json";
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(Gson gson, ModelMapper modelMapper, SupplierRepository supplierRepository) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.supplierRepository = supplierRepository;
    }

    private String readInformationFromFile() throws IOException {
        return Files.readString(Path.of(SUPPLIER_FILE_PATH));
    }

    @Override
    public void registerSuppliers() throws IOException {
        SupplierDto[] suppliers = this.gson
                .fromJson(this.readInformationFromFile(), SupplierDto[].class);

        for (SupplierDto supplierDto : suppliers) {
            Supplier supplier = this.modelMapper.map(supplierDto, Supplier.class);
            this.supplierRepository.saveAndFlush(supplier);
        }

    }
}
