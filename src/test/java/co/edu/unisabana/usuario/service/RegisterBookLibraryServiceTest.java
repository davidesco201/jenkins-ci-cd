package co.edu.unisabana.usuario.service;

import co.edu.unisabana.usuario.repository.dao.entity.BookEntity;
import co.edu.unisabana.usuario.service.library.RegisterBookLibrary;
import co.edu.unisabana.usuario.service.library.model.Book;
import co.edu.unisabana.usuario.service.library.model.CategoryBook;
import co.edu.unisabana.usuario.service.library.port.AddBookPort;
import co.edu.unisabana.usuario.service.library.port.RegisterBookPort;
import co.edu.unisabana.usuario.service.library.port.SearchBookPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RegisterBookLibraryServiceTest {
    @InjectMocks
    private RegisterBookLibrary registerBookLibraryService;

    @Mock
    private AddBookPort addBookPort;

    @Mock
    private RegisterBookPort registerBookPort;

    @Mock
    private SearchBookPort searchBookPort;

    @Test
    public void Given_book_exists_When_BookLibraryRegister_Then_return_1() {
        Book book = new Book("El ladrón del rayo", 2005, "Rick Riordan", false, CategoryBook.SOFT_COVER);
        Mockito.when(searchBookPort.validateExistsBook(book.getName())).thenReturn(true);

        int result = registerBookLibraryService.registerBook(book);

        Mockito.verify(addBookPort).addBook(book.getName());
        assertEquals(1, result);
    }

    @Test
    public void Given_book_doesnt_exists_When_BookLibraryRegister_Then_return_2() {
        Book book = new Book("El ladrón del rayo", 2005, "Rick Riordan", false, CategoryBook.SOFT_COVER);
        Mockito.when(searchBookPort.validateExistsBook(book.getName())).thenReturn(false);

        int result = registerBookLibraryService.registerBook(book);

        Mockito.verify(registerBookPort).registerBook(book);
        assertEquals(2, result);
    }

    @Test
    public void Given_author_name_When_getBooksByAuthor_Then_return_data_size_2(){
        List<BookEntity> listBooks = new ArrayList<>();
        BookEntity book = new BookEntity("Juego de Tronos", 1996, "George R.R. Martin", true, "digital", 0, 1);
        BookEntity bookTwo = new BookEntity("Choque de reyes", 1998, "George R.R. Martin", true, "suave", 0, 1);
        listBooks.add(book);
        listBooks.add(bookTwo);

        Mockito.when(searchBookPort.getBooksByAuthor("George R.R. Martin")).thenReturn(listBooks);

        assertEquals(2, registerBookLibraryService.getBooksByAuthor("George R.R. Martin").size());
    }

    @Test
    public void Given_category_When_getBooksByCategory_Then_return_data_size_2(){
        List<BookEntity> listBooks = new ArrayList<>();
        BookEntity book = new BookEntity("Juego de Tronos", 1996, "George R.R. Martin", true, "digital", 0, 1);
        BookEntity bookTwo = new BookEntity("Choque de reyes", 1998, "George R.R. Martin", true, "digital", 0, 1);
        listBooks.add(book);
        listBooks.add(bookTwo);

        Mockito.when(searchBookPort.getBooksByCategory("digital")).thenReturn(listBooks);

        assertEquals(2, registerBookLibraryService.getBooksByCategory("digital").size());
    }
}
