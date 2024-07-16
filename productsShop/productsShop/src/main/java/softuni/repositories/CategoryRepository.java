package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
