package softuni.services.impl;

import com.google.gson.Gson;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.dto.PartDto;
import softuni.dto.PartRootDto;
import softuni.dto.PartXmlDto;
import softuni.models.Part;
import softuni.repositories.PartRepository;
import softuni.services.PartService;
import softuni.util.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PartServiceImpl implements PartService {
    private final String PART_FILE_PATH = "C:\\Users\\Legion\\Desktop\\carDealership\\carDealership\\src\\main\\resources\\static\\files\\json\\parts.json";
    private final String PART_FILE_XML_PATH = "C:\\Users\\Legion\\Desktop\\carDealership\\carDealership\\src\\main\\resources\\static\\files\\xml\\parts.xml";
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final PartRepository partRepository;
    public PartServiceImpl(Gson gson, XmlParser xmlParser, ModelMapper modelMapper, PartRepository partRepository) {
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.partRepository = partRepository;
    }
    private String readInformationFromFile() throws IOException {
        return Files.readString(Path.of(PART_FILE_PATH));
    }

    @Override
    public void registerParts() throws IOException {
        PartDto[] parts = this.gson
                .fromJson(this.readInformationFromFile(), PartDto[].class);

        for (PartDto partDto : parts) {
            Part part = this.modelMapper.map(partDto, Part.class);
            this.partRepository.saveAndFlush(part);
        }

    }

    @Override
    public void registerXmlParts() throws JAXBException, IOException {
        PartRootDto partRootDto = this.xmlParser
                .parseXml(PartRootDto.class, this.PART_FILE_XML_PATH);

        for (PartXmlDto partXmlDto : partRootDto.getParts()) {
            Part part = this.modelMapper.map(partXmlDto, Part.class);
            this.partRepository.saveAndFlush(part);
        }

    }
}
