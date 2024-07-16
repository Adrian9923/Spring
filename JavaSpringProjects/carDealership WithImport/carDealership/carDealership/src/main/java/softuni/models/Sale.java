package softuni.models;

import jakarta.persistence.*;

@Entity
@Table(name = "sales")
public class Sale extends BaseEntity{
    private float discount;
    private Car car;
    private Customer customer;

    public Sale() {
    }

    @Column
    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    @OneToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
