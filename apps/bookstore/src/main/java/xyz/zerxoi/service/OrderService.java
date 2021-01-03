package xyz.zerxoi.service;

import xyz.zerxoi.pojo.Cart;

public interface OrderService {
    public String create(Cart cart, Integer userId);
}
