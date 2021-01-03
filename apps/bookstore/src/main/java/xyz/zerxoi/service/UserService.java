package xyz.zerxoi.service;

import xyz.zerxoi.pojo.User;

public interface UserService {
    public void register(User user);

    public User login(User user);

    public boolean existsUsername(String username);
}
