package com.test.Project.models;

public enum UserRole {

    ADMIN,USER;

    public String getRole()
    {
        return "ROLE_" + this.name();
    }
}
