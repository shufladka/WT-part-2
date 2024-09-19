package by.bsuir.domain;

import java.util.Date;

public class User {
    private String username;
    private String password;
    private String name;
    private String surname;
    private Role role;
    private Date dateOfBirth;
    private String email;

    public User(String username, String password, String name, String surname, Role role, Date dateOfBirth, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    public User() {}

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "[User: username: " + this.username +
                ", name: " + this.name +
                ", surname: " + this.surname +
                ", role: " + this.role +
                ", birthday: " + this.dateOfBirth +
                ", email: " + this.email + "]";
    }
}
