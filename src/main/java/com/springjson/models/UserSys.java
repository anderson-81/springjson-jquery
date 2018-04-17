package com.springjson.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "USERSYS")
@SequenceGenerator(name = "SeqUserId", sequenceName = "SEQUSERID", initialValue = 1, allocationSize = 1)
public class UserSys implements Serializable {

    private static final long serialVersionUID = -2829554041536248493L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int integer;

    @Column(name = "NAME", nullable = false, unique = true, length = 200)
    private String name;

    @Column(name = "USERNAME", nullable = false, unique = true, length = 200)
    private String username;

    @Column(name = "PASSWORD", nullable = false, length = 200)
    private String password;

    public UserSys() {
    }

    public int getInteger() {
        return integer;
    }

    public void setInteger(int integer) {
        this.integer = integer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public UserSys GetUserSys(String username, String password){
        this.username = username;
        this.password = password;
        return this;
    }
}
