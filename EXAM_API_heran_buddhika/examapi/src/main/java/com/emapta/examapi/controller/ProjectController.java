package com.emapta.examapi.controller;

import com.emapta.examapi.dto.ProjectDTO;
import com.emapta.examapi.exception.EntityNotFoundException;
import com.emapta.examapi.model.Project;
import com.emapta.examapi.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exam-api")
@Validated
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/projects/{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable("projectId") long projectId) throws EntityNotFoundException {
        Optional<Project> project = this.projectService.findProjectById(projectId);

        if (project.isPresent()) {
            return new ResponseEntity<>(project.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/projects", consumes = {MediaType.ALL_VALUE})
    public ResponseEntity<Project> createProject(@RequestBody @Valid ProjectDTO newProjectDTO) {
        try {
            Project project = this.projectService.createProject(newProjectDTO);
            return new ResponseEntity<>(project, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/projects")
    public ResponseEntity<List<Project>> findAllProjects() {
        try {
            List<Project> allProjects = this.projectService.findAllProjects();
            return new ResponseEntity<>(allProjects, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
