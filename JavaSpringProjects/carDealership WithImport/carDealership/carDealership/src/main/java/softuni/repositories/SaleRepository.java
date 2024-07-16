package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.models.Sale;


public interface SaleRepository extends JpaRepository<Sale, Long> {
}
