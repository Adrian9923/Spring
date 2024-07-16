package softuni.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryRootDto {
    @XmlElement(name = "category")
    private List<CategoryXmlDto> categories;

    public CategoryRootDto() {
    }

    public List<CategoryXmlDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryXmlDto> categories) {
        this.categories = categories;
    }
}
