package com.elk.domain.model.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum Role {

    ADMINISTRATOR(Arrays.asList(Privilege.values())),
    PROJECT_MANAGER(Arrays.stream(Privilege.values())
            .filter(priv -> priv.isOfCategory(Privilege.Category.PROJECT) || priv.isOfCategory(Privilege.Category.TASK))
            .collect(Collectors.toList())),
    DEVELOPER(List.of(Privilege.EDIT_TASK_STATUS, Privilege.EDIT_TASK_PROGRESS, Privilege.EDIT_TASK_DESCRIPTION));

    private final Collection<Privilege> privileges;

    Role(Collection<Privilege> privileges) {
        this.privileges = privileges;
    }
}
