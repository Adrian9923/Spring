package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.models.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
}
