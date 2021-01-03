// package xyz.zerxoi.dao;

// import java.math.BigDecimal;

// import org.junit.Test;

// import xyz.zerxoi.dao.impl.BookDaoImpl;
// import xyz.zerxoi.pojo.Book;

// public class BookDaoTest {
//     private BookDao bookDao = new BookDaoImpl();

//     @Test
//     public void insertBookTest() {
//         bookDao.insertBook(new Book(null, "深入理解Java虚拟机 第三版", "周志明", new BigDecimal(129.00), 300000, 1000000, null));
//     }

//     @Test
//     public void updateBookTest() {
//         bookDao.updateBook(new Book(21, "深入理解Java虚拟机 第三版", "周志明", new BigDecimal(60.00), 300000, 0, null));
//     }

//     @Test
//     public void queryBookByIdTest() {
//         System.out.println(bookDao.queryBookById(21));
//     }

//     @Test
//     public void deleteBookByIdTest() {
//         bookDao.deleteBookById(21);
//     }
    
//     @Test
//     public void queryBooksTest() {
//         for (Book book : bookDao.queryBooks()) {
//             System.out.println(book);
//         }
//     }
// }
