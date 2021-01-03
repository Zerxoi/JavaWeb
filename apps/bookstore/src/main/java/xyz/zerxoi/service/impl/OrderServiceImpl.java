package xyz.zerxoi.service.impl;

import java.util.Date;
import java.util.Map;

import xyz.zerxoi.dao.BookDao;
import xyz.zerxoi.dao.OrderDao;
import xyz.zerxoi.dao.OrderItemDao;
import xyz.zerxoi.dao.impl.BookDaoImpl;
import xyz.zerxoi.dao.impl.OrderDaoImpl;
import xyz.zerxoi.dao.impl.OrderItemDaoImpl;
import xyz.zerxoi.pojo.Book;
import xyz.zerxoi.pojo.Cart;
import xyz.zerxoi.pojo.CartItem;
import xyz.zerxoi.pojo.OderItem;
import xyz.zerxoi.pojo.Order;
import xyz.zerxoi.service.OrderService;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemdao = new OrderItemDaoImpl();
    private BookDao bookdao = new BookDaoImpl();

    @Override
    public String create(Cart cart, Integer userId) {
        String orderId = System.currentTimeMillis() + "" + userId;
        Order order = new Order(orderId, 0, cart.getTotalPrice(), new Date(), userId);
        orderDao.saveOrder(order);
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            CartItem cartItem = entry.getValue();
            orderItemdao.saveOrderItem(new OderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), orderId));
            Book book = bookdao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookdao.updateBook(book);
        }
        cart.clear();
        return orderId;
    }

}
