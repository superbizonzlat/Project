package com.test.Project.models;

public enum UserRole {

    ADMIN,USER,CLIENT;

    public String getRole()
    {
        return "ROLE_" + this.name();
    }
}
