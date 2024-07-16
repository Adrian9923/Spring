package softuni;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.services.*;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final CarService carService;
    private final CustomerService customerService;
    private final PartService partService;
    private final SaleService saleService;
    private final SupplierService supplierService;

    public ConsoleRunner(CarService carService, CustomerService customerService, PartService partService, SaleService saleService, SupplierService supplierService) {
        this.carService = carService;
        this.customerService = customerService;
        this.partService = partService;
        this.saleService = saleService;
        this.supplierService = supplierService;
    }


    @Override
    public void run(String... args) throws Exception {
       /* carService.registerCars();
        customerService.registerCustomers();
        partService.registerParts();
        saleService.registerSales();
        supplierService.registerSuppliers();

        */
        carService.registerXmlCars();
        customerService.registerXmlCustomers();
        partService.registerXmlParts();
        saleService.registerXmlSales();
        supplierService.registerXmlSuppliers();
    }
}
