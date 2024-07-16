package softuni.dto;

import com.google.gson.annotations.Expose;

public class SupplierDto {
    @Expose
    private boolean isImporter;
    @Expose
    private String name;

    public SupplierDto() {
    }

    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
