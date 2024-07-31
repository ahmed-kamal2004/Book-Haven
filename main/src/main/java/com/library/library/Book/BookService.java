package com.library.library.Book;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.library.Book.DTO.BookDTO;
import com.library.library.Exceptions.BookNotFoundException;
import com.library.library.Exceptions.IsbnExistsException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    @Autowired
    private final BookRepository bookRepository;

    public List<BookDTO> getAllBooks() {
        List<Book> books = this.bookRepository.findAll();
        return books.stream().map(this::convertBookToBookDTO).toList();
    }

    public BookDTO getBook(Integer Id) {
        Optional<Book> optionalBook = this.bookRepository.findById(Id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            return this.convertBookToBookDTO(book);
        }
        throw new BookNotFoundException();
    }

    public void createBook(BookDTO bookDTO) {
        this.checkBookISTB(bookDTO);
        Book book = this.convertBookDTOToBook(bookDTO);
        this.bookRepository.save(book);
    }

    public void updateBook(Integer Id, BookDTO bookDTO) {
        Optional<Book> book = this.bookRepository.findById(Id);
        System.out.println(Id);
        if (book.isPresent()) {
            Book new_book = this.convertBookDTOToBook(bookDTO);
            new_book.setID(Id);
            try {
                this.bookRepository.save(new_book);
            } catch (Exception e) {
                throw new IsbnExistsException();
            }
            return;
        }
        throw new BookNotFoundException();
    }

    @Transactional("transactionManager") // for handling the deletion operation
    public void deleteBook(Integer id) {
        Optional<Book> book = this.bookRepository.findById(id);

        if (book.isPresent()) {
            this.bookRepository.delete(book.get());
            return;
        }
        throw new BookNotFoundException();
    }

    private Book convertBookDTOToBook(BookDTO bookDto) {
        return Book.builder()
                .ISBN(bookDto.getISBN())
                .author(bookDto.getAuthor())
                .available(bookDto.getAvailable())
                .publicationYear(bookDto.getPublicationYear())
                .title(bookDto.getTitle())
                .build();
    }

    private BookDTO convertBookToBookDTO(Book book) {
        return BookDTO.builder()
                .ID(book.getID())
                .ISBN(book.getISBN())
                .author(book.getAuthor())
                .available(book.getAvailable())
                .publicationYear(book.getPublicationYear())
                .title(book.getTitle())
                .build();
    }

    private void checkBookISTB(BookDTO bookDTO) throws IsbnExistsException {
        Optional<Book> search_book = this.bookRepository.findByISBN(bookDTO.getISBN());
        if (search_book.isPresent()) {
            throw new IsbnExistsException();
        }
        return;
    }
}
