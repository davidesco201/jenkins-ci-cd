package co.edu.unisabana.usuario.service.library.port;

import co.edu.unisabana.usuario.repository.dao.entity.BookEntity;
import co.edu.unisabana.usuario.service.library.model.Book;

import java.util.List;

public interface SearchBookPort {

    boolean validateExistsBook(String nameBook);
    List<BookEntity> getBooksByAuthor(String authorName);
    List<BookEntity> getBooksByCategory(String category);
}
