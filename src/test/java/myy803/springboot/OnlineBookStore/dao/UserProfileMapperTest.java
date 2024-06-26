package myy803.springboot.OnlineBookStore.dao;

import myy803.springboot.OnlineBookStore.model.UserProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class UserProfileMapperTest {

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Test
    public void testFindByUsername() {
        // Mock a UserProfile
        UserProfile userProfile = new UserProfile(/* Set necessary properties */);
        userProfile.setUsername("username");
        // Save the UserProfile to the database
        userProfileMapper.save(userProfile);
        // Perform the search
        Optional<UserProfile> foundUserProfile = userProfileMapper.findByUsername("username");
        // Assert the result
        assertEquals(userProfile, foundUserProfile.orElse(null));
    }
}
