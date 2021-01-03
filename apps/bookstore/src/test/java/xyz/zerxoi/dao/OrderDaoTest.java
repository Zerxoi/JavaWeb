package xyz.zerxoi.dao;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

import xyz.zerxoi.dao.impl.OrderDaoImpl;
import xyz.zerxoi.pojo.Order;

public class OrderDaoTest {
    OrderDao orderDao = new OrderDaoImpl();
    @Test
    public void saveOrderTest() {
        // orderDao.saveOrder(new Order("1231", 0, new BigDecimal(99.8), new Date(), 1));
        // orderDao.saveOrder(new Order("fasf", 2, new BigDecimal(19.8), new Date(), 1));
    }
}
