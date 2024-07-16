package softuni.dto;

import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;

public class CustomerDto {
    @Expose
    private LocalDateTime birthDate;
    @Expose
    private String name;
    @Expose
    private boolean isYoungDriver;

    public CustomerDto() {
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
