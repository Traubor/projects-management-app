package com.elk.persistance.model;

import com.elk.persistance.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;

@Entity
@Table(name = "task")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Task implements Comparable<Task> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @NotNull
    private Project project;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    @Null
    private User assignee;

    @Column(name = "description", nullable = false)
    @NotNull
    private String description;

    @Column(name = "progress", nullable = false)
    @NotNull
    private BigDecimal progress;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    @Column(name = "deadline", nullable = false)
    @NotNull
    private LocalDate deadline;

    @Override
    public int compareTo(Task o) {
        return Comparator.comparing(Task::getStatus)
                .thenComparing(Task::getDeadline)
                .thenComparing(Task::getDescription)
                .compare(this, o);
    }
}
