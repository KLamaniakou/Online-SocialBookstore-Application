package myy803.springboot.OnlineBookStore.service;

import myy803.springboot.OnlineBookStore.dao.RequestDAO;
import myy803.springboot.OnlineBookStore.model.bookrequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService{
    @Autowired
    private RequestDAO RequestDAO;
    @Override
    public void save(bookrequest bookrequest) {
        RequestDAO.save(bookrequest);
    }
}
