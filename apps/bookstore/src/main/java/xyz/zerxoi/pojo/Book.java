package xyz.zerxoi.pojo;

import java.math.BigDecimal;

public class Book {
    private Integer id;
    private String name;
    private String author;
    private BigDecimal price;
    private Integer sales;
    private Integer stock;
    private String img = "static/img/default.jpg";

    public Book() {
    }

    public Book(Integer id, String name, String author, BigDecimal price, Integer sales, Integer stock, String img) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        if (img != null && img.equals("")) {
            this.img = img;
        }
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        if (img != null && img.equals("")) {
            this.img = img;
        }
    }

    @Override
    public String toString() {
        return "Book [author=" + author + ", id=" + id + ", img=" + img + ", name=" + name + ", price=" + price
                + ", sales=" + sales + ", stock=" + stock + "]";
    }
}
