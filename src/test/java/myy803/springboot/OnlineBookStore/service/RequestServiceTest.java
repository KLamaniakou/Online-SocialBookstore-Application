package myy803.springboot.OnlineBookStore.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import myy803.springboot.OnlineBookStore.dao.RequestDAO;
import myy803.springboot.OnlineBookStore.model.bookrequest;

import static org.mockito.Mockito.*;

public class RequestServiceTest {

    @Mock
    private RequestDAO requestDAO;

    @InjectMocks
    private RequestServiceImpl requestService;

    @Test
    public void testSave() {
        // Initialize the mocks manually
        MockitoAnnotations.openMocks(this);

        // Create a sample book request
        bookrequest request = new bookrequest();
        // Optionally initialize fields of the bookrequest object

        // Call the save method of the service
        requestService.save(request);

        // Verify that the save method of the DAO was called with the correct argument
        verify(requestDAO).save(request);
    }
}
