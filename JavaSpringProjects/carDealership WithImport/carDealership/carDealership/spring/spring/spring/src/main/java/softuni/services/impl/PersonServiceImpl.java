package softuni.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.models.Person;
import softuni.repositories.PersonRepository;
import softuni.services.PersonService;
import softuni.dto.PersonDto;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PersonServiceImpl implements PersonService {

    private final String PEOPLE_FILE_PATH = "C:\\Users\\Legion\\Desktop\\spring\\spring\\src\\main\\resources\\static\\files\\json\\people.json";

    private final Gson gson;
    private final ModelMapper modelMapper;
    private final PersonRepository personRepository;

    public PersonServiceImpl(Gson gson, ModelMapper modelMapper, PersonRepository personRepository) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.personRepository = personRepository;
    }

    private String readInformationFromFile() throws IOException {
        return Files.readString(Path.of(PEOPLE_FILE_PATH));
    }

    @Override
    public void registerPeople() throws IOException {
        PersonDto[] people = this.gson
                .fromJson(this.readInformationFromFile(), PersonDto[].class);

        for (PersonDto personDto : people) {
            Person person = this.modelMapper.map(personDto, Person.class);
            this.personRepository.saveAndFlush(person);
        }

    }
}
