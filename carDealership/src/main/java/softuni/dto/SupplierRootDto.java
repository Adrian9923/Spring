package softuni.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierRootDto {
    @XmlElement(name = "supplier")
    private List<SupplierXmlDto> suppliers;

    public SupplierRootDto() {
    }

    public List<SupplierXmlDto> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierXmlDto> suppliers) {
        this.suppliers = suppliers;
    }
}
