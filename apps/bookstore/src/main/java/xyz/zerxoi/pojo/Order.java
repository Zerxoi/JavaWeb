package xyz.zerxoi.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private String id;
    private Integer status;
    private BigDecimal price;
    private Date date;
    private Integer userId;

    public Order() {
    }

    public Order(String id, Integer status, BigDecimal price, Date date, Integer userId) {
        this.id = id;
        this.status = status;
        this.price = price;
        this.date = date;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order [date=" + date + ", id=" + id + ", price=" + price + ", status=" + status + ", userId=" + userId
                + "]";
    }

}
