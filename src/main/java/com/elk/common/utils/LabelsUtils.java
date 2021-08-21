package com.elk.common.utils;

import com.elk.domain.model.User;

public class LabelsUtils {

    private static final String USER_PLACEHOLDER = "%s %s (%s)";

    public static String userLabel(User user){
        return String.format(USER_PLACEHOLDER, user.getName(), user.getSurname(), user.getUsername());
    }
}
