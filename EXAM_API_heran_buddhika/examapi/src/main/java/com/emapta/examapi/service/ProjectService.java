package com.emapta.examapi.service;

import com.emapta.examapi.dto.ProjectDTO;
import com.emapta.examapi.exception.EntityNotFoundException;
import com.emapta.examapi.model.Project;
import com.emapta.examapi.respository.IssueRepository;
import com.emapta.examapi.respository.ProjectRepository;
import com.emapta.examapi.util.ObjectMapperUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Project createProject(ProjectDTO projectDTO) {
        Project project = ObjectMapperUtils.map(projectDTO, Project.class);
        return this.projectRepository.save(project);
    }

    public Optional<Project> findProjectById(long projectId) {
        Optional<Project> project = this.projectRepository.findById(projectId);
        if (!project.isPresent())
            throw new EntityNotFoundException(Project.class, "id", String.valueOf(projectId));
        return project;
    }

    public List<Project> findAllProjects() {
        return this.projectRepository.findAll();
    }
}
