package softuni.services.impl;

import com.google.gson.Gson;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.dto.SupplierDto;
import softuni.dto.SupplierRootDto;
import softuni.dto.SupplierXmlDto;
import softuni.models.Supplier;
import softuni.repositories.SupplierRepository;
import softuni.services.SupplierService;
import softuni.util.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final String SUPPLIER_FILE_PATH = "C:\\Users\\Legion\\Desktop\\carDealership\\carDealership\\src\\main\\resources\\static\\files\\json\\suppliers.json";
    private final String SUPPLIER_FILE_XML_PATH = "C:\\Users\\Legion\\Desktop\\carDealership\\carDealership\\src\\main\\resources\\static\\files\\xml\\suppliers.xml";
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(Gson gson, XmlParser xmlParser, ModelMapper modelMapper, SupplierRepository supplierRepository) {
        this.gson = gson;
        this.xmlParser = xmlParser;
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

    @Override
    public void registerXmlSuppliers() throws JAXBException, IOException {
        SupplierRootDto supplierRootDto = this.xmlParser
                .parseXml(SupplierRootDto.class, this.SUPPLIER_FILE_XML_PATH);

        for (SupplierXmlDto supplierXmlDto : supplierRootDto.getSuppliers()) {
            Supplier supplier = this.modelMapper.map(supplierXmlDto, Supplier.class);
            this.supplierRepository.saveAndFlush(supplier);
        }

    }
}
