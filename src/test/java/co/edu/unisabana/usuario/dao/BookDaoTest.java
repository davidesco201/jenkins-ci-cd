package co.edu.unisabana.usuario.dao;

import co.edu.unisabana.usuario.repository.dao.BookDao;
import co.edu.unisabana.usuario.repository.dao.entity.BookEntity;
import co.edu.unisabana.usuario.service.library.model.Book;
import co.edu.unisabana.usuario.service.library.model.CategoryBook;
import co.edu.unisabana.usuario.service.library.port.AddBookPort;
import co.edu.unisabana.usuario.service.library.port.RegisterBookPort;
import co.edu.unisabana.usuario.service.library.port.SearchBookPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
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
        assertThrows(IllegalArgumentException.class, () -> {
            dao.addBook("David Bowie");
        });
    }

}
