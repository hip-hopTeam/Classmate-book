package service;

import dao.BaseDAO;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public boolean deleteUser(long userId){
        User user = userBaseDAO.get(User.class, userId);
        userBaseDAO.delete(user);
        return true;
    }

    public List<User> getUserList(){
        List<User> users=userBaseDAO.find("From User");
        return users;
    }
}
