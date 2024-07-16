package softuni.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerRootDto {
    @XmlElement(name = "customer")
    private List<CustomerXmlDto> customers;

    public CustomerRootDto() {
    }

    public List<CustomerXmlDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerXmlDto> customers) {
        this.customers = customers;
    }
}
