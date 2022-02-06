package com.emapta.examapi.controller;

import com.emapta.examapi.model.ChangeLog;
import com.emapta.examapi.service.ChangeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exam-api")
public class ChangeLogController {

    @Autowired
    ChangeLogService changeLogService;

    @GetMapping("/changelogs/issue/{issueId}")
    public ResponseEntity<List<ChangeLog>> getChangeLogByIssueId(@PathVariable("issueId") long issueId, Pageable pageable) {
        List<ChangeLog> changeLogs = changeLogService.findChangeLogByIssueId(issueId, pageable);

        if (changeLogs != null) {
            return new ResponseEntity<>(changeLogs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
