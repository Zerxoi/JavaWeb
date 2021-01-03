package xyz.zerxoi.pojo;

import java.math.BigDecimal;

import org.junit.Test;

public class CartTest {
    @Test
    public void addTest() {
        Cart cart = new Cart();

        cart.add(new CartItem(1, "Java从入门到精通", 1, new BigDecimal(49)));
        cart.add(new CartItem(2, "深入理解Java虚拟机", 2, new BigDecimal(128)));
        cart.add(new CartItem(1, "Java从入门到精通", 1, new BigDecimal(49)));
        cart.add(new CartItem(3, "算法导论", 2, new BigDecimal(100)));
        
        System.out.println(cart);
    }

    @Test
    public void deleteTest() {
        Cart cart = new Cart();

        cart.add(new CartItem(1, "Java从入门到精通", 1, new BigDecimal(49)));
        cart.add(new CartItem(2, "深入理解Java虚拟机", 2, new BigDecimal(128)));
        cart.add(new CartItem(1, "Java从入门到精通", 1, new BigDecimal(49)));
        cart.add(new CartItem(3, "算法导论", 2, new BigDecimal(100)));

        cart.delete(1);
        System.out.println(cart);
        cart.delete(2);
        System.out.println(cart);
    }

    @Test
    public void clearTest() {
        Cart cart = new Cart();
        cart.add(new CartItem(1, "Java从入门到精通", 1, new BigDecimal(49)));
        cart.add(new CartItem(2, "深入理解Java虚拟机", 2, new BigDecimal(128)));
        cart.add(new CartItem(1, "Java从入门到精通", 1, new BigDecimal(49)));
        cart.add(new CartItem(3, "算法导论", 2, new BigDecimal(100)));

        cart.delete(1);
        cart.delete(2);
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCountTest() {
        Cart cart = new Cart();
        cart.add(new CartItem(1, "Java从入门到精通", 1, new BigDecimal(49)));
        cart.add(new CartItem(2, "深入理解Java虚拟机", 2, new BigDecimal(128)));
        cart.add(new CartItem(1, "Java从入门到精通", 1, new BigDecimal(49)));
        cart.add(new CartItem(3, "算法导论", 2, new BigDecimal(100)));

        cart.delete(1);
        cart.delete(2);
        cart.clear();
        cart.add(new CartItem(1, "Java从入门到精通", 1, new BigDecimal(49)));
        cart.updateCount(1, 20);
        System.out.println(cart);
    }
}
