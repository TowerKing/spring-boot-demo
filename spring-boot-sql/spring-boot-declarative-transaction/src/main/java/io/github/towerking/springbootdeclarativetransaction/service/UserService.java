package io.github.towerking.springbootdeclarativetransaction.service;

public interface UserService {

    void insertUser();

    void insertThenRollback() throws Exception;

    void invokeInsertThenRollback() throws Exception;

}
