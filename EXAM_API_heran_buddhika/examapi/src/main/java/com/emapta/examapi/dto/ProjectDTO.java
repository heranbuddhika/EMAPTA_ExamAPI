package com.emapta.examapi.dto;

import javax.validation.constraints.NotNull;

public class ProjectDTO {

    private long projectId;

    @NotNull(message = "Project Name is mandatory.")
    private String projectName;

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
