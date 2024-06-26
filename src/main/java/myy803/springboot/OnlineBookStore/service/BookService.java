package myy803.springboot.OnlineBookStore.service;


import myy803.springboot.OnlineBookStore.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService{
    public void saveUserBook(Book book);
    public List<Book> findAll();
    public void deleteById(int theId);
}
