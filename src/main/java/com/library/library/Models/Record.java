package com.library.library.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Table(name = "record")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Record {
    @Id
    private RecordKey id;

    private Date returnDate;

    private Boolean stillBorrowed;
}
