package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.models.Part;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
}
