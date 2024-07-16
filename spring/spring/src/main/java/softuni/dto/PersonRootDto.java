package softuni.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "people")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonRootDto {
    @XmlElement(name = "person")
    private List<PersonXmlDto> people;

    public PersonRootDto() {
    }

    public List<PersonXmlDto> getPeople() {
        return people;
    }

    public void setPeople(List<PersonXmlDto> people) {
        this.people = people;
    }
}
