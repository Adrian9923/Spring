package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
