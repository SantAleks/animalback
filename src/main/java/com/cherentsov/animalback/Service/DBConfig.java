package com.cherentsov.animalback.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:application.properties")
@Component
public class DBConfig {


    private String dialect;
    private String driver_class;
    private String url;
    private String username;
    private String password;
    private String show_sql;
    private String hbm2ddl;

    private static DBConfig instance;

    static synchronized DBConfig getInstance() {
        if (instance == null) {
            instance = new DBConfig();
        }
        return instance;
    }

    @Autowired
    public void setDialect(@Value("${hibernate.dialect}")String value) {
        DBConfig.getInstance().dialect = value;
    }

    @Autowired
    public void setDriver_class(@Value("${hibernate.connection.driver_class}")String value) {
        DBConfig.getInstance().driver_class = value;
    }

    @Autowired
    public void setUrl(@Value("${hibernate.connection.url}")String value) {
        DBConfig.getInstance().url = value;
    }

    @Autowired
    public void setUsername(@Value("${hibernate.connection.username}")String value) {
        DBConfig.getInstance().username = value;
    }

    @Autowired
    public void setPassword(@Value("${hibernate.connection.password}")String value) {
        DBConfig.getInstance().password = value;
    }

    @Autowired
    public void setShow_sql(@Value("${hibernate.show_sql}")String value) {
        DBConfig.getInstance().show_sql = value;
    }

    @Autowired
    public void setHbm2ddl(@Value("${hibernate.hbm2ddl.auto}")String value) {
        DBConfig.getInstance().hbm2ddl = value;
    }

    String getDialect() {
        return dialect;
    }

    String getDriver_class() {
        return driver_class;
    }

    String getUrl() {
        return url;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    String getShow_sql() {
        return show_sql;
    }

    String getHbm2ddl() {
        return hbm2ddl;
    }

    @Override
    public String toString() {
        return "DBConfig{" +
                "dialect='" + dialect + '\'' +
                ", driver_class='" + driver_class + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", show_sql='" + show_sql + '\'' +
                ", hbm2ddl='" + hbm2ddl + '\'' +
                '}';
    }
}
