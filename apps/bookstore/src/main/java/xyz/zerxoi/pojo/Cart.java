package xyz.zerxoi.pojo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Integer, CartItem> items = new HashMap<>();

    public Cart() {
    }

    public Cart(Map<Integer, CartItem> items) {
        this.items = items;
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (CartItem item : items.values()) {
            totalCount += item.getCount();
        }
        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);

        for (CartItem item : items.values()) {
            totalPrice = totalPrice.add(item.getTotalPrice());
        }

        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Cart [items=" + items + ", totalCount=" + getTotalCount() + ", totalPrice=" + getTotalPrice() + "]";
    }

    public void add(CartItem cartItem) {
        CartItem item = items.get(cartItem.getId());
        if (item != null) {
            item.setCount(item.getCount() + 1);
        } else {
            items.put(cartItem.getId(), cartItem);
        }
    }

    public void delete(Integer id) {
        items.remove(id);
    }

    public void clear() {
        items.clear();
    }

    public void updateCount(Integer id, Integer count) {
        CartItem item = items.get(id);
        if (item != null) {
            item.setCount(count);
        }
    }
}
