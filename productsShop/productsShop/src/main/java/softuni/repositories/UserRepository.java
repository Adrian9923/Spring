package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
