package service;

import dao.BaseDAO;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by CoderQiang on 2017/11/4.
 */
@Service
public class UserService {

    @Autowired
    BaseDAO<User> userBaseDAO;

    public boolean addUser(User user) {
        userBaseDAO.save(user);
        return true;
    }

}
