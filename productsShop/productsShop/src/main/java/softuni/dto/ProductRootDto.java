package softuni.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductRootDto {
    @XmlElement(name = "product")
    private List<ProductXmlDto> products;

    public ProductRootDto() {
    }

    public List<ProductXmlDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductXmlDto> products) {
        this.products = products;
    }
}
