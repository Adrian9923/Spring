package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
