package service;

import dao.UserDao;
import model.UserOrm;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public int createUser(UserOrm user) {
        return this.userDao.saveUser(user);
    }
}
