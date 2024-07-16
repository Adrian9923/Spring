package onlineShop.models.products.computers;

import onlineShop.common.constants.ExceptionMessages;
import onlineShop.common.constants.OutputMessages;
import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.Product;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseComputer extends BaseProduct implements Computer {
    private List<Component> components;
    private List<Peripheral> peripherals;

    protected BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return this.peripherals;
    }

    @Override
    public double getOverallPerformance() {
        if (this.getComponents().isEmpty()) {
            return super.getOverallPerformance();
        }else {
            return super.getOverallPerformance() + this.calculateComponentsAveragePerformance();
        }

    }

    @Override
    public double getPrice() {
        return super.getPrice() + this.calculateComponentsPrice() + this.calculatePeripheralsPrice();
    }

    @Override
    public void addComponent(Component component) {
        for (Component c : this.getComponents()) {
            if (c.getClass().getSimpleName().equals(component.getClass().getSimpleName())) {
                throw new IllegalArgumentException(String.format(ExceptionMessages.EXISTING_COMPONENT,
                        component.getClass().getSimpleName(),
                        this.getClass().getSimpleName(),
                        this.getId()));
            }
        }
        this.getComponents().add(component);
    }

    @Override
    public Component removeComponent(String componentType) {
        int index = -1;
        for (int i = 0; i < this.getComponents().size(); i++) {
            if (this.getComponents().get(i).getClass().getSimpleName().equals(componentType)) {
                index = i;
                break;
            }
        }
        if (this.getComponents().isEmpty() || index == -1) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_COMPONENT,
                    componentType,
                    this.getClass().getSimpleName(),
                    this.getId()));
        }
        return this.getComponents().remove(index);
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        for (Peripheral p : this.getPeripherals()) {
            if (p.getClass().getSimpleName().equals(peripheral.getClass().getSimpleName())) {
                throw new IllegalArgumentException(String.format(ExceptionMessages.EXISTING_PERIPHERAL,
                        peripheral.getClass().getSimpleName(),
                        this.getClass().getSimpleName(),
                        this.getId()));
            }
        }
        this.getPeripherals().add(peripheral);

    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        int index = -1;
        for (int i = 0; i < this.getPeripherals().size(); i++) {
            if (this.getPeripherals().get(i).getClass().getSimpleName().equals(peripheralType)) {
                index = i;
                break;
            }
        }
        if (this.getPeripherals().isEmpty() || index == -1) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_PERIPHERAL,
                    peripheralType,
                    this.getClass().getSimpleName(),
                    this.getId()));
        }
        return this.getPeripherals().remove(index);
    }


    private double calculateComponentsAveragePerformance() {
        return this.getComponents().stream()
                .mapToDouble(Product::getOverallPerformance)
                .average()
                .orElse(0);
    }

    private double calculateComponentsPrice() {
        return this.getComponents()
                .stream()
                .mapToDouble(Component::getPrice)
                .sum();
    }

    private double calculatePeripheralsPrice() {
        return this.getPeripherals()
                .stream()
                .mapToDouble(Peripheral::getPrice)
                .sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString())
                .append(System.lineSeparator());
        sb.append(" ").append(String.format(OutputMessages.COMPUTER_COMPONENTS_TO_STRING, this.getComponents().size()))
                .append(System.lineSeparator());
        this.getComponents()
                .forEach(component -> sb.append("  ").append(component.toString()).append(System.lineSeparator()));
        sb.append(" ").append(String.format(OutputMessages.COMPUTER_PERIPHERALS_TO_STRING, this.getPeripherals().size(),
                this.calculatePeripheralsAveragePerformance()))
                .append(System.lineSeparator());
        this.getPeripherals()
                .forEach(peripheral -> sb.append("  ").append(peripheral.toString()).append(System.lineSeparator()));
        return sb.toString().trim();
    }

    private double calculatePeripheralsAveragePerformance() {
        return this.getPeripherals()
                .stream()
                .mapToDouble(Product::getOverallPerformance)
                .average()
                .orElse(0);
    }
}
