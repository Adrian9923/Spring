package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.models.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
