package myy803.springboot.OnlineBookStore.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import myy803.springboot.OnlineBookStore.dao.UserProfileMapper;
import myy803.springboot.OnlineBookStore.forms.UserProfileFormData;
import myy803.springboot.OnlineBookStore.model.UserProfile;
import java.util.Optional;
import static org.mockito.Mockito.*;

public class UserProfileServiceTest {
    @Mock
    UserProfileMapper userProfileMapper ;

    @InjectMocks
    UserProfileServiceImpl userProfileService ;

    @Test
    public void testRetrieveProfile_UserExists() {
        // Initialize the mocks manually
        MockitoAnnotations.openMocks(this);

        // Create a sample user profile
        String username = "testuser";
        UserProfile userProfile = new UserProfile();
        userProfile.setFullName("John");
        userProfile.setAddress("123 Main St");
        userProfile.setAge(30);
        userProfile.setPhoneNumber("1234567890");
        userProfile.setCategory("Standard");
        userProfile.setAuthor("tsoxlas");

        UserProfileFormData userProfileFormData = new UserProfileFormData();

        when(userProfileMapper.findByUsername(username)).thenReturn(Optional.of(userProfile));

        UserProfileFormData result = userProfileService.retrieveProfile(username, userProfileFormData);

        assertNotNull(result);
        assertEquals("John", result.getFullName());
        assertEquals("123 Main St", result.getAddress());
        assertEquals(30, result.getAge());
        assertEquals("1234567890", result.getPhoneNumber());
        assertEquals("Standard", result.getCategory());
        assertEquals("tsoxlas", result.getAuthor());
    }

    @Test
    public void testRetrieveProfile_UserDoesNotExist() {
        // Initialize the mocks manually
        MockitoAnnotations.openMocks(this);

        String username = "testuser";
        UserProfileFormData userProfileFormData = new UserProfileFormData();

        when(userProfileMapper.findByUsername(username)).thenReturn(Optional.empty());

        UserProfileFormData result = userProfileService.retrieveProfile(username, userProfileFormData);

        assertNull(result);
    }

    @Test
    public void testSave_UserExists() {
        // Initialize the mocks manually
        MockitoAnnotations.openMocks(this);

        String username = "testuser";
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername(username);

        UserProfileFormData userProfileFormData = new UserProfileFormData();
        userProfileFormData.setFullName("Jane");
        userProfileFormData.setAddress("456 Elm St");
        userProfileFormData.setAge(28);
        userProfileFormData.setPhoneNumber("9876543210");
        userProfileFormData.setCategory("Premium");
        userProfileFormData.setAuthor("lamaniakou");

        when(userProfileMapper.findByUsername(username)).thenReturn(Optional.of(userProfile));

        userProfileService.save(userProfileFormData, userProfile);

        verify(userProfileMapper).save(userProfile);
        assertEquals("Jane", userProfile.getFullName());
        assertEquals("456 Elm St", userProfile.getAddress());
        assertEquals(28, userProfile.getAge());
        assertEquals("9876543210", userProfile.getPhoneNumber());
        assertEquals("Premium", userProfile.getCategory());
        assertEquals("lamaniakou", userProfile.getAuthor());
    }

    @Test
    public void testSave_UserDoesNotExist() {
        // Initialize the mocks manually
        MockitoAnnotations.openMocks(this);

        String username = "testuser";
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername(username);

        UserProfileFormData userProfileFormData = new UserProfileFormData();
        userProfileFormData.setFullName("Jane");
        userProfileFormData.setAddress("456 Elm St");
        userProfileFormData.setAge(28);
        userProfileFormData.setPhoneNumber("9876543210");
        userProfileFormData.setCategory("Premium");
        userProfileFormData.setAuthor("tsoxlas");

        when(userProfileMapper.findByUsername(username)).thenReturn(Optional.empty());

        userProfileService.save(userProfileFormData, userProfile);

        verify(userProfileMapper).save(userProfile);
        assertEquals("Jane", userProfile.getFullName());
        assertEquals("456 Elm St", userProfile.getAddress());
        assertEquals(28, userProfile.getAge());
        assertEquals("9876543210", userProfile.getPhoneNumber());
        assertEquals("Premium", userProfile.getCategory());
        assertEquals("tsoxlas", userProfile.getAuthor());
    }
}
