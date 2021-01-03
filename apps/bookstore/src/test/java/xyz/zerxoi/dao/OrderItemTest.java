package xyz.zerxoi.dao;

import java.math.BigDecimal;

import org.junit.Test;

import xyz.zerxoi.dao.impl.OrderItemDaoImpl;
import xyz.zerxoi.pojo.OderItem;

public class OrderItemTest {
    OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Test
    public void saveOrderItemTest() {
        // orderItemDao.saveOrderItem(new OderItem(null, "name", 2, new BigDecimal(99.1), "123"));
        // orderItemDao.saveOrderItem(new OderItem(null, "dfa", 2, new BigDecimal(99.1), "asb"));

    }
}
