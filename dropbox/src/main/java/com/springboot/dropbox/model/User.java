package com.springboot.dropbox.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.*;

@Entity
public class User {



    private String firstName;

    private String lastName;

    @Id
    private String emailID;

    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "UserAccess", joinColumns = @JoinColumn(name = "emailID"), inverseJoinColumns = @JoinColumn(name = "filePath"))
    private List<File> files;

    {
        files = new ArrayList<File>();
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<File> getFiles() {
        System.out.println("get files of " + firstName + " is called");
        return files;
    }


    public void setFiles(List<File> files) {
        this.files = files;
    }

}
