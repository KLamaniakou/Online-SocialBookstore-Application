package myy803.springboot.OnlineBookStore.dao;


import myy803.springboot.OnlineBookStore.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserProfileMapper extends JpaRepository<UserProfile, Long>{
	Optional<UserProfile> findByUsername(String username);
}

