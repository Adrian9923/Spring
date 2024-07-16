package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
