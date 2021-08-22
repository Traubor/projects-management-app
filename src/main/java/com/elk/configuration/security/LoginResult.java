package com.elk.configuration.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
class LoginResult {
    private final String code;
}
