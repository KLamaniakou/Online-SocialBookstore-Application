package myy803.springboot.OnlineBookStore.dao;

import myy803.springboot.OnlineBookStore.model.bookrequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestDAO extends JpaRepository<bookrequest,Integer> {
    List<bookrequest> findByUsername(String username);

    List<bookrequest> findByOwner(String username);

    List<bookrequest> findById(int id);

    List<bookrequest> findByTitleAndOwner(String title,String username);

}
