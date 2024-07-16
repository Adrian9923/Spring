import entities.User;
import orm.Connector;
import orm.EntityManager;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
            throws SQLException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {

        Scanner scanner = new Scanner(System.in);

        String username = scanner.nextLine().trim();
        String password = scanner.nextLine().trim();
        String db = scanner.nextLine();

        Connector.createConnection(username,password,db);
        EntityManager<User> em = new EntityManager<>(Connector.getConnection());

        User userJames = new User("james", "password", 32, LocalDate.of(2011, 11, 1));
        User userJohn = new User("john", "password", 17, LocalDate.of(2019, 5, 7));
        User userRobert = new User("robert", "password", 41, LocalDate.of(2017, 9, 4));

        em.persist(userJames);
        em.persist(userJohn);
        em.persist(userRobert);

        User found = em.findFirst(User.class);
        System.out.printf("The first user: %s\n", found.getUsername());

        User findFirstUserUnder18 = em.findFirst(User.class, " where age < 18");
        System.out.printf("The first user under 18 years old: %s\n", findFirstUserUnder18.getUsername());

        Iterable<User> allPersistedUsers = em.find(User.class);
        System.out.println("The first users:");
        for (User user : allPersistedUsers) {
            System.out.printf("\tFound user: %s\n",user.getUsername());
        }


        Iterable<User> allUsersOver18 = em.find(User.class, " where age > 18");
        System.out.println("All persisted users with age greater than 18:");
        for (User user : allUsersOver18) {
            System.out.printf("\tFound user: %s\n",user.getUsername());
        }

    }
}
