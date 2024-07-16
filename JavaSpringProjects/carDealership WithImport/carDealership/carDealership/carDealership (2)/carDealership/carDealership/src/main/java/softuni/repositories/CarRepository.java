package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.models.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
}
