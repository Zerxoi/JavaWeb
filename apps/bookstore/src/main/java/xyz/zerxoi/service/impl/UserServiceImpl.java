package xyz.zerxoi.service.impl;

import xyz.zerxoi.dao.UserDao;
import xyz.zerxoi.dao.impl.UserDaoImpl;
import xyz.zerxoi.pojo.User;
import xyz.zerxoi.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean existsUsername(String username) {
        return userDao.queryUserByUsername(username) != null;
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public void register(User user) {
        userDao.saveUser(user);
    }
}
