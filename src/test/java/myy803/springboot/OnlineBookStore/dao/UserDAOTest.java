package myy803.springboot.OnlineBookStore.dao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import myy803.springboot.OnlineBookStore.model.User;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;

    @Test
    public void testFindByUsername() {
        // Mock a user
        User user = new User();
        user.setUsername("username");
        // Save the user to the database
        userDAO.save(user);
        // Perform the search
        Optional<User> foundUser = userDAO.findByUsername("username");
        // Assert the result
        assertEquals(user, foundUser.orElse(null));
    }
}
