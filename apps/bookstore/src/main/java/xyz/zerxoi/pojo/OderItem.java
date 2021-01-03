package xyz.zerxoi.pojo;

import java.math.BigDecimal;

public class OderItem {
    private Integer id;
    private String name;
    private Integer count;
    private BigDecimal price;
    private String oderId;

    public OderItem() {
    }

    public OderItem(Integer id, String name, Integer count, BigDecimal price, String oderId) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.oderId = oderId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getOderId() {
        return oderId;
    }

    public void setOderId(String oderId) {
        this.oderId = oderId;
    }

    public BigDecimal getTotalPrice() {
        return price.multiply(new BigDecimal(count));
    }

    @Override
    public String toString() {
        return "OderItem [count=" + count + ", id=" + id + ", name=" + name + ", oderId=" + oderId + ", price=" + price
                + "]";
    }
}
