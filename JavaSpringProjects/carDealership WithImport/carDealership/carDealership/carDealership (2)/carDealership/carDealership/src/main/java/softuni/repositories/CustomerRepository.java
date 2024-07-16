package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
