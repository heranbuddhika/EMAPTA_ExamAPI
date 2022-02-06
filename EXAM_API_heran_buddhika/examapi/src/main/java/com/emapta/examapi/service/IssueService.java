package com.emapta.examapi.service;

import com.emapta.examapi.enums.State;
import com.emapta.examapi.model.ChangeLog;
import com.emapta.examapi.model.Issue;
import com.emapta.examapi.model.User;
import com.emapta.examapi.respository.IssueRepository;
import com.emapta.examapi.respository.UserRepository;
import com.emapta.examapi.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class IssueService {

    @Autowired
    private ChangeLogService changeLogService;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;

    public Issue createIssue(Issue issue) {
        // Making the current status as 'open' at first
        issue.setCurrentState(State.OPEN.getText());
        Issue save = this.issueRepository.save(issue);

        if (save != null) {
            // Create new change log for the Issue
            changeLogService.createChangeLog(createChangeLogObj(save, save.getUser()));
        }
        return save;
    }

    public Optional<Issue> findIssueById(long issueId) {
        return this.issueRepository.findById(issueId);
    }

    public Issue assignNewUser(long issueId, long userId) {
        Optional<Issue> existingIssue = Optional.of(issueRepository.getById(issueId));
        Optional<User> existingUser = Optional.of(userRepository.getById(userId));

        if (!existingIssue.isPresent() && !existingUser.isPresent()) {
            // ERROR
            return null;
        }
        Issue issue = existingIssue.get();
        issue.setUser(existingUser.get());
        return issueRepository.save(issue);
    }

    private ChangeLog createChangeLogObj(Issue issue, User user) {
        ChangeLog changeLog = new ChangeLog();
        changeLog.setChangedOn(Utils.formatCurrentDate());
        changeLog.setFromState(State.OPEN);
        changeLog.setToState(State.OPEN);
        changeLog.setIssue(issue);
        changeLog.setUser(user);
        return changeLog;
    }

    private List<ChangeLog> createChangeLogObj(State currentState, State toState) {
        ChangeLog changeLog = new ChangeLog();
        changeLog.setChangedOn(Utils.formatCurrentDate());
        return Arrays.asList(changeLog);
    }
}
