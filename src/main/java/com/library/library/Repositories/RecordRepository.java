package com.library.library.Repositories;

import com.library.library.ModelsDB.Record;
import com.library.library.ModelsDB.RecordKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, RecordKey> {
}
