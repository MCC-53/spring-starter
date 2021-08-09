package com.mcc53jpa.configs;

public enum ApplicationUserPermission {
    EMPLOYEE_READ("employee:read"),
    EMPLOYEE_WRITE("employee:write"),
    DEPARTMENT_READ("department:read"),
    DEPARTMENT_WRITE("department:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
