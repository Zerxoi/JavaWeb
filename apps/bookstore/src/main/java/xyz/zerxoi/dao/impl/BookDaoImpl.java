package xyz.zerxoi.dao.impl;

import java.util.List;

import xyz.zerxoi.dao.BookDao;
import xyz.zerxoi.pojo.Book;

public class BookDaoImpl extends BaseDao implements BookDao {

    @Override
    public int insertBook(Book book) {
        String sql = "insert into t_book(`name`, `author`, `price`, `sales`, `stock`, `img`) values (?, ?, ?, ?, ?, ?)";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(),
                book.getImg());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where `id` = ?";
        return update(sql, id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set `name` = ?, `author` = ?, `price` = ?, `sales` = ?, `stock` = ?, `img` = ? where `id` = ?";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(),
                book.getImg(), book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select * from t_book where `id` = ?";
        return queryForOne(Book.class, sql, id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select * from t_book";
        return queryForList(Book.class, sql);
    }

    @Override
    public int queryBookToal() {
        String sql = "select count(*) from t_book";
        return queryForSingleValue(long.class, sql).intValue();
    }

    @Override
    public List<Book> queryPageBooks(Integer page, Integer size) {
        String sql = "select * from t_book limit ?, ?";
        return queryForList(Book.class, sql, (page - 1) * size, size);
    }

    @Override
    public int queryBookToalBetweenPrice(Integer min, Integer max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        return queryForSingleValue(long.class, sql, min, max).intValue();
    }

    @Override
    public List<Book> queryPageBooksBetweenPrice(Integer page, Integer size, Integer min, Integer max) {
        String sql = "select * from t_book where price between ? and ? order by price limit ?, ?";
        return queryForList(Book.class, sql, min, max, (page - 1) * size, size);
    }

}
