package com.library.library.Repositories;

import com.library.library.Models.Record;
import com.library.library.Models.RecordKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, RecordKey> {
}
