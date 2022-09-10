package co.edu.unisabana.usuario.dao;

import co.edu.unisabana.usuario.repository.dao.BookDao;
import co.edu.unisabana.usuario.repository.dao.entity.BookEntity;
import co.edu.unisabana.usuario.service.library.model.Book;
import co.edu.unisabana.usuario.service.library.model.CategoryBook;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BookDaoTest {
    @InjectMocks
    BookDao dao;

    @Test
    public void Given_book_list_validate_book_exists_When_book_dao_Then_book_exists(){
        Book book = new Book("David Bowie", 2001, "David Espitia", true, CategoryBook.SOFT_COVER);
        dao.registerBook(book);

        boolean result = dao.validateExistsBook("David Bowie");
        assertTrue(result);
    }

    @Test
    public void Given_book_list_When_book_dao_Then_book_add_successful(){
        Book book = new Book("David Bowie", 2001, "David Espitia", true, CategoryBook.SOFT_COVER);
        dao.registerBook(book);

        boolean result = dao.addBook(book.getName());
        assertTrue(result);
    }

    @Test
    public void Given_book_list_When_book_dao_Then_book_add_then_exception(){
        assertThrows(IllegalArgumentException.class, () -> dao.addBook("David Bowie"));
    }

    @Test
    public void Given_author_name_When_getBooksByAuthor_then_book_list(){
        List<BookEntity> listBooks;
        Book book = new Book("Harry Potter y la piedra filosofal", 1997, "J.K. Rowling", true, CategoryBook.HARD_COVER);
        Book bookTwo = new Book("Harry Potter y la cámara secreta", 1998, "J.K. Rowling", true, CategoryBook.HARD_COVER);
        Book bookThree = new Book("La selección", 2012, "Kiera Cass", true, CategoryBook.EBOOK);
        dao.registerBook(book);
        dao.registerBook(bookTwo);
        dao.registerBook(bookThree);

        listBooks = dao.getBooksByAuthor("J.K. Rowling");
        assertEquals(2, listBooks.size());
    }

    @Test
    public void Given_category_When_getBooksByCategory_then_book_list(){
        List<BookEntity> listBooks;
        Book book = new Book("Harry Potter y la piedra filosofal", 1997, "J.K. Rowling", true, CategoryBook.HARD_COVER);
        Book bookTwo = new Book("Harry Potter y la cámara secreta", 1998, "J.K. Rowling", true, CategoryBook.HARD_COVER);
        Book bookThree = new Book("Harry Potter y el prisionero de Azkaban", 1999, "J.K. Rowling", true, CategoryBook.SOFT_COVER);
        dao.registerBook(book);
        dao.registerBook(bookTwo);
        dao.registerBook(bookThree);

        listBooks = dao.getBooksByCategory(CategoryBook.HARD_COVER.name());
        assertEquals(2, listBooks.size());
    }

    @Test
    public void Given_book_When_add_more_than_ten_books_Then_false(){
        Book book = new Book("Harry Potter y la piedra filosofal", 1997, "J.K. Rowling", true, CategoryBook.HARD_COVER);
        dao.registerBook(book);
        for(int i = 0; i < 9; i++)
            dao.addBook("Harry Potter y la piedra filosofal");

        assertThrows(IllegalArgumentException.class, () -> dao.addBook("Harry Potter y la piedra filosofal"));
    }
}
