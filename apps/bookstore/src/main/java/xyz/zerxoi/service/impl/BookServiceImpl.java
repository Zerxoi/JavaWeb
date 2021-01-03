package xyz.zerxoi.service.impl;

import java.util.List;

import xyz.zerxoi.dao.BookDao;
import xyz.zerxoi.dao.impl.BookDaoImpl;
import xyz.zerxoi.pojo.Book;
import xyz.zerxoi.pojo.Page;
import xyz.zerxoi.service.BookService;

public class BookServiceImpl implements BookService {
    private BookDao bookdao = new BookDaoImpl();

    @Override
    public void insertBook(Book book) {
        bookdao.insertBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookdao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookdao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookdao.queryBookById(id);
    }

    // @Override
    // public List<Book> queryBooks() {
    //     return bookdao.queryBooks();
    // }

    @Override
    public Page<Book> page(Integer page, Integer size, String url) {
        int total = bookdao.queryBookToal();
        int pageTotal = total / size;
        if (total % size > 0) {
            pageTotal++;
        }
        if (page < 1) {
            page = 1;
        } else if (page > pageTotal) {
            page = pageTotal;
        }
        List<Book> items = bookdao.queryPageBooks(page, size);
        return new Page<Book>(page, pageTotal, total, size, items, url);
    }

    @Override
    public Page<Book> pageBetweenPrice(Integer page, Integer size, Integer min, Integer max, String url) {
        int total = bookdao.queryBookToalBetweenPrice(min, max);
        int pageTotal = total / size;
        if (total % size > 0) {
            pageTotal++;
        }
        if (page <= 1) {
            page = 1;
        } else if (page > pageTotal) {
            page = pageTotal;
        }
        List<Book> items = bookdao.queryPageBooksBetweenPrice(page, size, min, max);
        return new Page<>(page, pageTotal, total, size, items, url);
    }
    
}
