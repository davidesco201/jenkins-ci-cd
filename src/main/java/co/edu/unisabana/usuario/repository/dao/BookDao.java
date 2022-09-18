package co.edu.unisabana.usuario.repository.dao;

import co.edu.unisabana.usuario.repository.dao.entity.BookEntity;
import co.edu.unisabana.usuario.exception.PreliminaryRegisterException;
import co.edu.unisabana.usuario.service.library.model.Book;
import co.edu.unisabana.usuario.service.library.port.AddBookPort;
import co.edu.unisabana.usuario.service.library.port.RegisterBookPort;
import co.edu.unisabana.usuario.service.library.port.SearchBookPort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Repository
public class BookDao implements SearchBookPort, RegisterBookPort, AddBookPort {

    static List<BookEntity> listBooks = new ArrayList<>();
    static final int MAX_QUANTITY_BOOK_ADDED = 10;
    @Override
    public boolean validateExistsBook(String nameBook) {
        AtomicBoolean exists = new AtomicBoolean(false);
        listBooks.forEach(book -> {
            if (book.getName().equals(nameBook)) {
                exists.set(true);
            }
        });
        return exists.get();
    }

    @Override
    public List<BookEntity> getBooksByAuthor(String authorName) {
        return listBooks.stream()
                .filter(bookEntity -> Objects.equals(bookEntity.getAuthor(), authorName))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookEntity> getBooksByCategory(String category) {
        return listBooks.stream()
                .filter(bookEntity -> Objects.equals(bookEntity.getCategory().toUpperCase(), category.toUpperCase()))
                .collect(Collectors.toList());
    }

    @Override
    public void registerBook(Book newBook) {
        BookEntity bookEntity = BookEntity.fromModel(newBook);
        bookEntity.setId(listBooks.size() + 1);
        listBooks.add(bookEntity);
    }

    @Override
    public boolean addBook(String name) {
        for (BookEntity book : listBooks) {
            if (book.getName().equals(name) && book.getQuantity() < MAX_QUANTITY_BOOK_ADDED) {
                book.setQuantity(book.getQuantity() + 1);
                return true;
            }
        }
        //Para evitar confusiones futuras, se debe crear excepciones personalizadas conforme existan reglas de negocio
        //que las ameriten. POR FAVOR AGREGUEN CONTEXTO
        throw new PreliminaryRegisterException(name);
    }

}
