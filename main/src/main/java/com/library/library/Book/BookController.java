package com.library.library.Book;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.Book.DTO.BookDTO;
import com.library.library.Exceptions.BookNotFoundException;
import com.library.library.Exceptions.IsbnExistsException;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping("/api/books/")
@RequiredArgsConstructor
public class BookController {

    @Autowired
    private final BookService bookService;

    // ● GET /api/books: Retrieve a list of all books.
    @GetMapping("")
    public ResponseEntity<?> getAllBooks() {
        try {
            return new ResponseEntity<>(this.bookService.getAllBooks(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // ● GET /api/books/{id}: Retrieve details of a specific book by ID.
    @GetMapping("/{id}")
    public ResponseEntity<?> getMethodName(@Parameter(description = "id") @PathVariable(value = "id") Integer id) {
        try {
            return new ResponseEntity<>(this.bookService.getBook(id), HttpStatus.OK);
        } catch (BookNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // ● POST /api/books: Add a new book to the library.
    @SuppressWarnings("rawtypes") // Just to overcome warnings
    @PostMapping("")
    public ResponseEntity<?> createBook(@RequestBody BookDTO bookDTO) {
        // TODO: process POST request
        try {
            this.bookService.createBook(bookDTO);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (IsbnExistsException e) {
            return ResponseEntity.badRequest().body("ISBN Already Exists");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // ● PUT /api/books/{id}: Update an existing book's information.
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202"),
            @ApiResponse(responseCode = "404"),
            @ApiResponse(responseCode = "400"),
            @ApiResponse(responseCode = "500")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@Parameter(description = "id") @PathVariable(value = "id") Integer id,
            @RequestBody BookDTO bookDTO) {
        try {
            this.bookService.updateBook(id, bookDTO);
            return ResponseEntity.accepted().build();
        } catch (BookNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IsbnExistsException e) {
            return ResponseEntity.badRequest().body("ISBN Already Exists Within Another Book");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // ● DELETE /api/books/{id}: Remove a book from the library.
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@Parameter(description = "id") @PathVariable(value = "id") Integer id) {
        try {
            this.bookService.deleteBook(id);
            return ResponseEntity.noContent().build();
        } catch (BookNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

}
