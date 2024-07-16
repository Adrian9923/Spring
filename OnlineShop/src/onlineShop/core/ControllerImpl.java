package onlineShop.core;

import onlineShop.common.constants.ExceptionMessages;
import onlineShop.common.constants.OutputMessages;
import onlineShop.core.interfaces.Controller;
import onlineShop.models.products.components.*;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.computers.DesktopComputer;
import onlineShop.models.products.computers.Laptop;
import onlineShop.models.products.peripherals.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private List<Computer> computers;
    private List<Component> components;
    private List<Peripheral> peripherals;

    public ControllerImpl() {
        this.computers = new ArrayList<>();
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    public List<Computer> getComputers() {
        return computers;
    }

    public List<Component> getComponents() {
        return components;
    }

    public List<Peripheral> getPeripherals() {
        return peripherals;
    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {
        for (Computer computer : this.getComputers()) {
            if (computer.getId() == id) {
                throw new IllegalArgumentException(ExceptionMessages.EXISTING_COMPUTER_ID);
            }
        }
        Computer computer;
        switch (computerType) {
            case "Laptop":
                computer = new Laptop(id, manufacturer, model, price);
                break;
            case "Desktop":
                computer = new DesktopComputer(id, manufacturer, model, price);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPUTER_TYPE);
        }

        this.getComputers().add(computer);
        return String.format(OutputMessages.ADDED_COMPUTER, id);
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        if (this.getComputerById(computerId) == null) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }
        for (Peripheral peripheral : this.getPeripherals()) {
            if (peripheral.getId() == id){
                throw new IllegalArgumentException(ExceptionMessages.EXISTING_PERIPHERAL_ID);
            }
        }

        Peripheral peripheral;
        switch (peripheralType) {
            case "Headset":
                peripheral = new Headset(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Keyboard":
                peripheral = new Keyboard(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Monitor":
                peripheral = new Monitor(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Mouse":
                peripheral = new Mouse(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_PERIPHERAL_TYPE);
        }
        this.getPeripherals().add(peripheral);
        this.getComputerById(computerId).addPeripheral(peripheral);
        return String.format(OutputMessages.ADDED_PERIPHERAL,peripheralType,id,computerId);
    }

    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        if (this.getComputerById(computerId) == null) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }

        boolean isRemoved = false;
        int peripheralId = -1;

        Computer computer = this.getComputerById(computerId);
        Peripheral removed = computer.removePeripheral(peripheralType);

        for (int i = 0; i < this.getPeripherals().size(); i++) {
            if (this.getPeripherals().get(i).getClass().getSimpleName().equals(peripheralType)) {
                peripheralId = this.getPeripherals().remove(i).getId();
                isRemoved = true;
                break;
            }
        }

        if (isRemoved && removed != null) {
            return String.format(OutputMessages.REMOVED_PERIPHERAL,peripheralType,peripheralId);
        }
        return null;
    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer, String model, double price, double overallPerformance, int generation) {
        if (this.getComputerById(computerId) == null) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }

        for (Component component : this.getComponents()) {
            if (component.getId() == id) {
                throw new IllegalArgumentException(ExceptionMessages.EXISTING_COMPONENT_ID);
            }
        }

        Component component;
        switch (componentType) {
            case "CentralProcessingUnit":
                component = new CentralProcessingUnit(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "Motherboard":
                component = new Motherboard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "PowerSupply":
                component = new PowerSupply(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "RandomAccessMemory":
                component = new RandomAccessMemory(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "SolidStateDrive":
                component = new SolidStateDrive(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "VideoCard":
                component = new VideoCard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPONENT_TYPE);
        }

        this.getComponents().add(component);
        this.getComputerById(computerId).addComponent(component);
        return String.format(OutputMessages.ADDED_COMPONENT,componentType, id, computerId);
    }

    @Override
    public String removeComponent(String componentType, int computerId) {
        Computer computer = this.getComputerById(computerId);
        if (computer == null) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }

        boolean isRemoved = false;
        int componentId = -1;

        Component removed = computer.removeComponent(componentType);

        for (int i = 0; i < this.getComponents().size(); i++) {
            if (this.getComponents().get(i).getClass().getSimpleName().equals(componentType)) {
                componentId = this.getComponents().remove(i).getId();
                isRemoved = true;
                break;
            }
        }

        if (isRemoved && removed != null) {
            return String.format(OutputMessages.REMOVED_COMPONENT,componentType,componentId);
        }
        return null;
    }

    @Override
    public String buyComputer(int id) {
        Computer computerById = this.getComputerById(id);
        if (computerById != null) {
            this.getComputers().remove(computerById);
            return computerById.toString();
        }

        throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);

    }

    @Override
    public String BuyBestComputer(double budget) {
        List<Computer> sorted = this.getComputers()
                .stream()
                .sorted((f, s) -> Double.compare(s.getOverallPerformance(), f.getOverallPerformance()))
                .collect(Collectors.toList());

        for (Computer computer : sorted) {
            if (computer.getPrice() == budget) {
                return this.buyComputer(computer.getId());
            }
        }

        throw new IllegalArgumentException(String.format(ExceptionMessages.CAN_NOT_BUY_COMPUTER,budget));
    }

    @Override
    public String getComputerData(int id) {
        Computer computer = this.getComputerById(id);
        if (computer != null) {
            return computer.toString();
        }
        throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
    }

    private Computer getComputerById(int id) {
        for (Computer computer : this.computers) {
            if (computer.getId() == id) {
                return computer;
            }
        }
        return null;

    }
}
