package com.elk.domain.project.service;

import com.elk.common.utils.LabelsUtils;
import com.elk.domain.model.Project;
import lombok.Getter;

@Getter
public class ProjectHeadingDto {

    public ProjectHeadingDto(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.code = project.getCode();
        this.projectManagerName = LabelsUtils.userLabel(project.getProjectManager());
    }

    private final Long id;
    private final String name;
    private final String code;
    private final String projectManagerName;
}
