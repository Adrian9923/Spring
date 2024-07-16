package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
