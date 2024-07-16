package softuni.dto;

import com.google.gson.annotations.Expose;

public class SaleDto {
    @Expose
    private float discount;

    public SaleDto() {
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
