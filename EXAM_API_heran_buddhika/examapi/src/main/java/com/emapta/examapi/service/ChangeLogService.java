package com.emapta.examapi.service;

import com.emapta.examapi.model.ChangeLog;
import com.emapta.examapi.respository.ChangeLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChangeLogService {

    @Autowired
    private ChangeLogRepository changeLogRepository;

    public List<ChangeLog> findChangeLogByIssueId(long issueId, Pageable pageable) {
        return this.changeLogRepository.findByIssue_issueId(issueId, pageable);
    }

    public ChangeLog createChangeLog(ChangeLog changeLogObj) {
        return this.changeLogRepository.save(changeLogObj);
    }
}
