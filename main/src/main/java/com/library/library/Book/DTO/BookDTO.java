package com.library.library.Book.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Integer ID;
    private String title;
    private String author;
    private Date publicationYear;
    private Integer ISBN;
    private Boolean available;
}
