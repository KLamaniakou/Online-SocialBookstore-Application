package myy803.springboot.OnlineBookStore.service;

import myy803.springboot.OnlineBookStore.model.bookrequest;
import org.springframework.stereotype.Service;

@Service
public interface RequestService {
    public void save(bookrequest bookrequest);
}
