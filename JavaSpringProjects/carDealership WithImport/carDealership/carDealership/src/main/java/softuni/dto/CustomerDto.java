package softuni.dto;

import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;

public class CustomerDto {
    @Expose
    private String name;
    @Expose
    private String birthDate;
    @Expose
    private boolean isYoungDriver;
    private LocalDateTime parsedBirthDate;

    public CustomerDto() {
    }

    public LocalDateTime getBirthDate() {
        return LocalDateTime.parse(birthDate);
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate.toString();
        this.parsedBirthDate = birthDate;
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
