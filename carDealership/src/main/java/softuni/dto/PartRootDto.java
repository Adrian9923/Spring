package softuni.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartRootDto {
    @XmlElement(name = "part")
    private List<PartXmlDto> parts;

    public PartRootDto() {
    }

    public List<PartXmlDto> getParts() {
        return parts;
    }

    public void setParts(List<PartXmlDto> parts) {
        this.parts = parts;
    }
}
