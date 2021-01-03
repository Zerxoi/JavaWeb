// package xyz.zerxoi.service;

// import java.math.BigDecimal;

// import org.junit.Test;

// import xyz.zerxoi.pojo.Book;
// import xyz.zerxoi.service.impl.BookServiceImpl;

// public class BookServiceTest {
//     BookService bookService = new BookServiceImpl();

//     @Test
//     public void insertBookTest() {
//         bookService.insertBook(new Book(null, "深入理解Java虚拟机 第三版", "周志明", new BigDecimal(129.00), 300000, 1000000, null));
//     }

//     @Test
//     public void updateBookTest() {
//         bookService.updateBook(new Book(21, "深入理解Java虚拟机 第三版", "周志明", new BigDecimal(60.00), 300000, 0, null));
//     }

//     @Test
//     public void queryBookByIdTest() {
//         System.out.println(bookService.queryBookById(21));
//     }

//     @Test
//     public void deleteBookByIdTest() {
//         bookService.deleteBookById(21);
//     }

//     @Test
//     public void queryBooksTest() {
//         for (Book book : bookService.queryBooks()) {
//             System.out.println(book);
//         }
//     }
// }