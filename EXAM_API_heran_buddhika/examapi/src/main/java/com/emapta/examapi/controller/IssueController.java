package com.emapta.examapi.controller;

import com.emapta.examapi.model.Issue;
import com.emapta.examapi.model.Project;
import com.emapta.examapi.service.ChangeLogService;
import com.emapta.examapi.service.IssueService;
import com.emapta.examapi.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/exam-api")
public class IssueController {

    @Autowired
    IssueService issueService;

    @Autowired
    ProjectService projectService;

    @GetMapping("/issues/{issueId}")
    public ResponseEntity<Issue> getProjectById(@PathVariable("issueId") long issueId) {
        Optional<Issue> issue = issueService.findIssueById(issueId);

        if (issue.isPresent()) {
            return new ResponseEntity<>(issue.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/issues")
    public ResponseEntity<Issue> createIssue(@RequestBody Issue newIssue) {
        try {
            Optional<Project> project = projectService.findProjectById(newIssue.getProject().getProjectId());
            newIssue.setProject(project.get());
            Issue issue = issueService
                    .createIssue(newIssue);
            return new ResponseEntity<>(issue, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/issues/{issueId}/user/{userId}")
    public ResponseEntity assignNewUser(@PathVariable("issueId") long issueId, @PathVariable("userId") long userId) {
        try {
            Issue issue = issueService.assignNewUser(issueId, userId);
            return new ResponseEntity<>(issue, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }
}
