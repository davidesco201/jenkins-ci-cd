package co.edu.unisabana.usuario.service.library;

import co.edu.unisabana.usuario.repository.dao.entity.BookEntity;
import co.edu.unisabana.usuario.service.library.model.Book;
import co.edu.unisabana.usuario.service.library.model.CategoryBook;
import co.edu.unisabana.usuario.service.library.port.AddBookPort;
import co.edu.unisabana.usuario.service.library.port.RegisterBookPort;
import co.edu.unisabana.usuario.service.library.port.SearchBookPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegisterBookLibrary {

    private final SearchBookPort searchBookPort;
    private final AddBookPort addBookPort;
    private final RegisterBookPort registerBookPort;

    private Suma suma;

    public RegisterBookLibrary(SearchBookPort searchBookPort, AddBookPort addBookPort, RegisterBookPort registerBookPort, Suma objectoInyectado) {
        this.searchBookPort = searchBookPort;
        this.addBookPort = addBookPort;
        this.registerBookPort = registerBookPort;
        this.suma = objectoInyectado;
    }


    public int registerBook(Book book) {
        suma.sumar();
        boolean exists = searchBookPort.validateExistsBook(book.getName());
        if (exists) {
            addBookPort.addBook(book.getName());
            return 1;
        } else {
            registerBookPort.registerBook(book);
            return 2;
        }
    }
    public List<Book> getBooksByAuthor(String authorName){
        return searchBookPort.getBooksByAuthor(authorName).stream()
                .map(bookEntity -> new Book(bookEntity.getName(), bookEntity.getYear(), bookEntity.getAuthor(), bookEntity.isRRated(), CategoryBook.fromString(bookEntity.getCategory())))
                .collect(Collectors.toList());
    }
    public List<Book> getBooksByCategory(String category){
        return searchBookPort.getBooksByCategory(category).stream()
                .map(bookEntity -> new Book(bookEntity.getName(), bookEntity.getYear(), bookEntity.getAuthor(), bookEntity.isRRated(), CategoryBook.fromString(bookEntity.getCategory())))
                .collect(Collectors.toList());
    }

}
