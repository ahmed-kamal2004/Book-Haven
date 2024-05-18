package com.library.library.Models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Date;


@Embeddable
public class recordID implements Serializable {

    @ManyToOne
    @JoinColumn( name = "book_id")
    private book bookID;

    @ManyToOne
    @JoinColumn(name = "patron_id")
    private patron patronID;

    private Date loadDate;
}
