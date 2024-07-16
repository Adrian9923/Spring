package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.models.Part;

public interface PartRepository extends JpaRepository<Part, Long> {
}
