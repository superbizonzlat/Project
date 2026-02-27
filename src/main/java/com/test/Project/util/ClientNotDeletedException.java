package com.test.Project.util;

public class ClientNotDeletedException extends RuntimeException {
    public ClientNotDeletedException(String msg)
    {
        super(msg);
    }
}
