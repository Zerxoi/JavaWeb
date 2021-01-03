package xyz.zerxoi.dao;

import java.util.List;

import xyz.zerxoi.pojo.Book;

public interface BookDao {
    public int insertBook(Book book);
    public int deleteBookById(Integer id);
    public int updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();
    // 总数
    public int queryBookToal();
    // 页面图书
    public List<Book> queryPageBooks(Integer page, Integer size);
    public int queryBookToalBetweenPrice(Integer min, Integer max);
    public List<Book> queryPageBooksBetweenPrice(Integer page, Integer size, Integer min, Integer max);
}
