package com.elk.domain.model;

import com.elk.domain.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class User implements Comparable<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

    @Column(name = "surname", nullable = false)
    @NotNull
    private String surname;

    @Column(name = "email", nullable = false)
    @NotNull
    private String email;

    @Column(name = "username", nullable = false)
    @NotNull
    private String username;

    @Column(name = "password", nullable = false)
    @NotNull
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;

    @OneToMany(mappedBy = "assignee")
    private Set<Task> tasks = new HashSet<>(0);

    @OneToMany(mappedBy = "projectManager")
    private Set<Project> managedProjects = new HashSet<>(0);

    @Override
    public int compareTo(User o) {
        return Comparator.comparing(User::getName)
                .thenComparing(User::getSurname)
                .thenComparing(User::getUsername)
                .compare(this, o);
    }
}
