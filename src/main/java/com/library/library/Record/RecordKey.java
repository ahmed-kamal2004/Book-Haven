package com.library.library.ModelsDB;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Date;


@Embeddable
public class RecordKey implements Serializable {

    @ManyToOne
    @JoinColumn( name = "book_id")
    private Book bookID;

    @ManyToOne
    @JoinColumn(name = "patron_id")
    private Patron patronID;

    private Date loadDate;
}
