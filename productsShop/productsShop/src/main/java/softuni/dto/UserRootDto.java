package softuni.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserRootDto {
    @XmlElement(name = "user")
    private List<UserXmlDto> users;

    public UserRootDto() {
    }

    public List<UserXmlDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserXmlDto> users) {
        this.users = users;
    }
}
