package com.library.library.Services;

import com.library.library.ModelsDB.Book;
import com.library.library.Repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    @Autowired
    private final BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return this.bookRepository.findAll();
    }
}
