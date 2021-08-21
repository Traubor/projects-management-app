package com.elk.domain.model.enums;

public enum Privilege {

    CREATE_USER(Category.USER),
    DELETE_USER(Category.USER),
    VIEW_USER(Category.USER),
    EDIT_USER(Category.USER),

    CREATE_PROJECT(Category.PROJECT),
    DELETE_PROJECT(Category.PROJECT),
    VIEW_PROJECT(Category.PROJECT),
    EDIT_PROJECT(Category.PROJECT),

    CREATE_TASK(Category.TASK),
    DELETE_TASK(Category.TASK),
    VIEW_TASK(Category.TASK),
    EDIT_TASK(Category.TASK),
    EDIT_TASK_ASSIGNEE(Category.TASK),
    EDIT_TASK_STATUS(Category.TASK),
    EDIT_TASK_PROGRESS(Category.TASK),
    EDIT_TASK_DEADLINE(Category.TASK),
    EDIT_TASK_DESCRIPTION(Category.TASK);

    private final Category category;

    Privilege(Category category) {
        this.category = category;
    }

    boolean isOfCategory(Category category) {
        return this.category == category;
    }

    public enum Category {USER, PROJECT, TASK}
}
