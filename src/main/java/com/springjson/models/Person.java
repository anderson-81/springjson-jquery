package com.springjson.models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "PERSON")
@SequenceGenerator(name = "SeqPersonId", sequenceName = "SEQPERSONID", initialValue = 1, allocationSize = 1)
public class Person implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;
    
    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;
    
    @Column(name = "SALARY", nullable = false, scale = 12, precision = 2)
    private Double salary;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTHDAY", nullable = false)
    private Date birthday;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", nullable = false, length = 1)
    private Gender gender;
    
    @Lob
    @Column(name = "PICTURE", nullable = true)
    private byte[] picture;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
    
    public Person GetPerson(String name, String email, String birthday, String salary, String gender, byte[] picture){
        this.name = name;
        this.email = email;
        this.salary = Double.parseDouble(salary);
        if("M".equals(gender))
            this.gender = Gender.M;
        else
            this.gender = Gender.F;
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            this.birthday = sdf.parse(birthday);
        }catch(ParseException e){
            System.err.println(e.getMessage());
        }
        this.picture = picture;
        return this;
    }
}
