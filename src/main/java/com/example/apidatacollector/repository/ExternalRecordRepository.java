package com.example.apidatacollector.repository;

import com.example.apidatacollector.domain.ExternalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalRecordRepository extends JpaRepository<ExternalRecord, Long> {
}
