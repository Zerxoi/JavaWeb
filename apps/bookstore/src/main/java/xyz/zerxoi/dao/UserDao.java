package xyz.zerxoi.dao;

import xyz.zerxoi.pojo.User;

public interface UserDao {
    public User queryUserByUsername(String username);

    public User queryUserByUsernameAndPassword(String username, String password);

    public int saveUser(User user);

}
