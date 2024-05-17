package com.library.library.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;
    private String title;
    private String author;
    private Date publicationYear;
    private Integer ISBN;
}
