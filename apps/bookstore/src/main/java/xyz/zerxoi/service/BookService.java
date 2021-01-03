package xyz.zerxoi.service;

import xyz.zerxoi.pojo.Book;
import xyz.zerxoi.pojo.Page;

public interface BookService {
    public void insertBook(Book book);
    public void deleteBookById(Integer id);
    public void updateBook(Book book);
    public Book queryBookById(Integer id);
    // public List<Book> queryBooks();
    public Page<Book> page(Integer page, Integer size, String url);
    public Page<Book> pageBetweenPrice(Integer page, Integer size, Integer min, Integer max, String url);
}
