package myy803.springboot.OnlineBookStore.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import myy803.springboot.OnlineBookStore.model.User;

public interface UserDAO extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);
}
