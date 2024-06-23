package com.library.library.Book;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class BookController {

    @Autowired
    private final BookService bookService;

    @GetMapping("getbooks")
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<>(this.bookService.getAllBooks(), HttpStatus.OK);

    }
}
