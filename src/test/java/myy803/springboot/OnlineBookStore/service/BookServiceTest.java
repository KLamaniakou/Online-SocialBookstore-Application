package myy803.springboot.OnlineBookStore.service;

import myy803.springboot.OnlineBookStore.dao.BookDAO;
import myy803.springboot.OnlineBookStore.model.Book;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Mock
    private BookDAO bookDAO;

    @InjectMocks
    private BookServiceImp bookService;


    @Test
    public void testSaveUserBook() {
        Book book = new Book(); // Create a sample book
        bookService.saveUserBook(book); // Call the method to be tested
        verify(bookDAO, times(1)).save(book); // Verify that BookDAO's save method is called once with the correct argument
    }

    @Test
    public void testFindAll() {
        List<Book> books = new ArrayList<>(); // Create a sample list of books
        when(bookDAO.findAll()).thenReturn(books); // Mock the behavior of BookDAO's findAll method
        List<Book> result = bookService.findAll(); // Call the method to be tested
        verify(bookDAO, times(1)).findAll(); // Verify that BookDAO's findAll method is called once
    }

    @Test
    public void testDeleteById() {
        int idToDelete = 123; // Sample ID to delete
        bookService.deleteById(idToDelete); // Call the method to be tested
        verify(bookDAO, times(1)).deleteById(idToDelete); // Verify that BookDAO's deleteById method is called once with the correct argument
    }
}
