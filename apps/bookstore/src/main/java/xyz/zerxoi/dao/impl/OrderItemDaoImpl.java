package xyz.zerxoi.dao.impl;

import xyz.zerxoi.dao.OrderItemDao;
import xyz.zerxoi.pojo.OderItem;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {

    @Override
    public int saveOrderItem(OderItem orderItem) {
        String sql = "insert into t_order_item(`name`, `count`, `price`, `order_id`) values (?, ?, ?, ?)";
        return update(sql, orderItem.getName(), orderItem.getCount(),orderItem.getPrice(), orderItem.getOderId());
    }
    
}
