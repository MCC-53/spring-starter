package com.nieceoftimes.serverside.enums;

public enum PrivilegeAuthority {
    CREATE_DEPARTMENT, // admin
    READ_DEPARTMENT,  // admin user
    UPDATE_DEPARTMENT, // admin
    DELETE_DEPARTMENT, // admin
    CREATE_PROJECT, // admin
    READ_PROJECT, // admin user
    UPDATE_PROJECT, // admin user
    DELETE_PROJECT, // admin
    READ_EMPLOYEE, // admin user
    UPDATE_EMPLOYEE, // user
    DELETE_EMPLOYEE // user
}
