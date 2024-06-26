package myy803.springboot.OnlineBookStore.service;

import myy803.springboot.OnlineBookStore.dao.BookDAO;
import myy803.springboot.OnlineBookStore.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServiceImp implements BookService{
    @Autowired
    private BookDAO BookDAO;
    @Override
    public void saveUserBook(Book book) {
        BookDAO.save(book);
    }

    @Override
    public List<Book> findAll() {
        return BookDAO.findAll();
    }

    @Override
    public void deleteById(int theId) {
        BookDAO.deleteById(theId);
    }
}
