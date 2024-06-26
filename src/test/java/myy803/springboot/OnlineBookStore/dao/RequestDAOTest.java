package myy803.springboot.OnlineBookStore.dao;

import myy803.springboot.OnlineBookStore.dao.RequestDAO;
import myy803.springboot.OnlineBookStore.model.bookrequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class RequestDAOTest {

    @Autowired
    private RequestDAO requestDAO;

    @Test
    public void testFindByUsername() {
        // Create some book requests and save them to the database
        bookrequest request1 = new bookrequest();
        request1.setUsername("username");
        requestDAO.save(request1);
        // Perform the search
        List<bookrequest> requestsByUsername = requestDAO.findByUsername("username");
        assertEquals(1, requestsByUsername.size());
    }

    @Test
    public void testFindByOwner() {
        // Create some book requests and save them to the database
        bookrequest request2 = new bookrequest();
        request2.setOwner("Owner");
        requestDAO.save(request2);
        // Perform the search
        List<bookrequest> requestsByOwner = requestDAO.findByOwner("Owner");
        assertEquals(1, requestsByOwner.size());
    }

    @Test
    public void testFindById() {
        // Create some book requests and save them to the database
        bookrequest request3 = new bookrequest();
        request3.setId(1);
        requestDAO.save(request3);
        // Perform the search
        List<bookrequest> requestsById = requestDAO.findById(1);
        assertEquals(1, requestsById.size());
    }
}
