package com.prototype.personal_manager.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EmployeeType {
    @JsonProperty("DETECTIVE")
    DETECTIVE,
    @JsonProperty("SECURITY")
    SECURITY;

    @Getter
    private String type;

}
