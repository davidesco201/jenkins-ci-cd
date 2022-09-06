package co.edu.unisabana.usuario.service;

import co.edu.unisabana.usuario.service.library.RegisterBookLibrary;
import co.edu.unisabana.usuario.service.library.model.Book;
import co.edu.unisabana.usuario.service.library.model.CategoryBook;
import co.edu.unisabana.usuario.service.library.port.AddBookPort;
import co.edu.unisabana.usuario.service.library.port.RegisterBookPort;
import co.edu.unisabana.usuario.service.library.port.SearchBookPort;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

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
    public void Given_book_exists_When_BookLibraryRegister_Then_successful() {
        Book book = new Book("El ladrón del rayo", 2005, "Rick Riordan", false, CategoryBook.SOFT_COVER);
        Mockito.when(searchBookPort.validateExistsBook(book.getName())).thenReturn(true);

        int result = registerBookLibraryService.registerBook(book);

        Mockito.verify(addBookPort).addBook(book.getName());
        assertEquals(1, result);
    }

    @Test
    public void Given_book_doesnt_exists_When_BookLibraryRegister_Then_successful() {
        Book book = new Book("El ladrón del rayo", 2005, "Rick Riordan", false, CategoryBook.SOFT_COVER);
        Mockito.when(searchBookPort.validateExistsBook(book.getName())).thenReturn(false);

        int result = registerBookLibraryService.registerBook(book);

        Mockito.verify(registerBookPort).registerBook(book);
        assertEquals(2, result);
    }
}
