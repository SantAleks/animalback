package com.cherentsov.animalback.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class DBConfigTest {

    @Test
    public void getInstance() {
        assertEquals(DBConfig.getInstance().getDialect(), "org.hibernate.dialect.H2Dialect");
        assertEquals(DBConfig.getInstance().getDriver_class(), "org.h2.Driver");
        assertEquals(DBConfig.getInstance().getUrl(), "jdbc:h2:./h2db");
        assertEquals(DBConfig.getInstance().getUsername(), "tully");
        assertEquals(DBConfig.getInstance().getPassword(), "tully");
        assertEquals(DBConfig.getInstance().getShow_sql(), "true");
        assertEquals(DBConfig.getInstance().getHbm2ddl(), "create");
        assertEquals(DBConfig.getInstance().toString().getClass(), String.class);
    }
}