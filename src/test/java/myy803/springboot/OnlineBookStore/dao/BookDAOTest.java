package myy803.springboot.OnlineBookStore.dao;

import myy803.springboot.OnlineBookStore.model.Book;
import myy803.springboot.OnlineBookStore.dao.BookDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class BookDAOTest {

    @Autowired
    private BookDAO bookDAO;

    @Test
    public void testFindByUsername() {
        // Save a book with a specific username
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthors("Test Author");
        book.setUsername("test_user");
        bookDAO.save(book);

        // Find books by the username
        List<Book> books = bookDAO.findByUsername("test_user");

        // Assert that only one book is returned and its attributes match
        assertEquals(1, books.size());
        assertEquals("Test Book", books.get(0).getTitle());
        assertEquals("Test Author", books.get(0).getAuthors());
        assertEquals("test_user", books.get(0).getUsername());
    }
}
