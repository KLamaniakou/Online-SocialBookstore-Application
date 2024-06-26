package myy803.springboot.OnlineBookStore.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import myy803.springboot.OnlineBookStore.dao.UserDAO;
import myy803.springboot.OnlineBookStore.model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;
import static org.mockito.Mockito.*;


public class UserServiceTest {

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    UserDAO userDAO;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void testSaveUser() {
        // Initialize the mocks manually
        MockitoAnnotations.openMocks(this);

        // Create a sample user
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");

        // Mock the password encoding
        when(bCryptPasswordEncoder.encode("password")).thenReturn("encodedPassword");

        // Call the saveUser method
        userService.saveUser(user);

        // Verify that the password was encoded and the user was saved
        verify(bCryptPasswordEncoder).encode("password");
        verify(userDAO).save(user);
        assertEquals("encodedPassword", user.getPassword());
    }

    @Test
    public void testIsUserPresent_UserExists() {
        // Initialize the mocks manually
        MockitoAnnotations.openMocks(this);

        // Create a sample user
        User user = new User();
        user.setUsername("testuser");

        // Mock the userDAO to return a user
        when(userDAO.findByUsername("testuser")).thenReturn(Optional.of(user));

        // Call the isUserPresent method
        boolean result = userService.isUserPresent(user);

        // Verify the result
        assertTrue(result);
        verify(userDAO).findByUsername("testuser");
    }

    @Test
    public void testIsUserPresent_UserDoesNotExist() {
        // Initialize the mocks manually
        MockitoAnnotations.openMocks(this);

        // Create a sample user
        User user = new User();
        user.setUsername("testuser");

        // Mock the userDAO to return an empty Optional
        when(userDAO.findByUsername("testuser")).thenReturn(Optional.empty());

        // Call the isUserPresent method
        boolean result = userService.isUserPresent(user);

        // Verify the result
        assertFalse(result);
        verify(userDAO).findByUsername("testuser");
    }

}
