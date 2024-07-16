package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.models.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
