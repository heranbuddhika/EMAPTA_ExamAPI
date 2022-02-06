package com.emapta.examapi.respository;

import com.emapta.examapi.model.ChangeLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChangeLogRepository extends JpaRepository<ChangeLog, Long> {
    List<ChangeLog> findByIssue_issueId(Long issueId, Pageable pageable);
}
