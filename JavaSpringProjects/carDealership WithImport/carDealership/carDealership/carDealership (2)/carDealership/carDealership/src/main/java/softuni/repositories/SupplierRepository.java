package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.models.Supplier;


public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
