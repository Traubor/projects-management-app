package com.elk.domain.model;

import com.elk.domain.project.service.ProjectDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "project")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class Project implements Comparable<Project> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false)
    private String code;

    @ManyToOne
    @JoinColumn(name = "project_manager_id", nullable = false)
    @NotNull
    private User projectManager;

    @OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)
    private Set<Task> tasks = new HashSet<>(0);

    @Override
    public int compareTo(Project o) {
        return Comparator.comparing(Project::getName)
                .compare(this, o);
    }

    @Transient
    public void update(ProjectDto projectDto, User projectManager) {
        this.name = projectDto.getName();
        this.code = projectDto.getCode();
        this.projectManager = projectManager;
    }
}
