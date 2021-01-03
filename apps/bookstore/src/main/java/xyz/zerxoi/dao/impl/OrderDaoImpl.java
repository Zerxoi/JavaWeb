package xyz.zerxoi.dao.impl;

import xyz.zerxoi.dao.OrderDao;
import xyz.zerxoi.pojo.Order;

public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`id`, `status`, `price`, `date`, `user_id`) values (?, ?, ?, ?, ?)";
        return update(sql, order.getId(), order.getStatus(), order.getPrice(), order.getDate(), order.getUserId());
    }
    
}
