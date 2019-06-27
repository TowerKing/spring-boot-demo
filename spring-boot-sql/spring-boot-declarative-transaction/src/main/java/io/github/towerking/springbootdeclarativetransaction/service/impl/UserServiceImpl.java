package io.github.towerking.springbootdeclarativetransaction.service.impl;

import io.github.towerking.springbootdeclarativetransaction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertUser() {
        jdbcTemplate.execute("insert into user (id, name) values (1, 'Hello')");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertThenRollback() throws Exception {
        jdbcTemplate.execute("insert into user (id, name) values (2, 'Hello Java')");
        throw new Exception();
    }

    @Override
    public void invokeInsertThenRollback() throws Exception {
        // success for transaction
        // (new UserServiceImpl()).insertThenRollback();

        // failure for transaction
        insertThenRollback();
    }
}
