package softuni;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.services.PersonService;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final PersonService personService;

    public ConsoleRunner(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public void run(String... args) throws Exception {
        personService.registerPeople();
    }
}
