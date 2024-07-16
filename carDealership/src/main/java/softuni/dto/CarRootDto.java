package softuni.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarRootDto {
    @XmlElement(name = "car")
    private List<CarXmlDto> cars;

    public CarRootDto() {
    }

    public List<CarXmlDto> getCars() {
        return cars;
    }

    public void setCars(List<CarXmlDto> cars) {
        this.cars = cars;
    }
}
