package net.csc625.checkin.models.pojos;


/**
 * Created by sristic on 12/4/17.
 */

public class User {

    private int userID;
    private String email;
    private String uid;
    private String lastLogin;
    private int invalidAttempts;
    private String active;
    private String firstName;
    private String lastName;

    public User () {

    }

    public User(String email, String uid, String lastLogin, int invalidAttempts, String active,
                String firstName, String lastName) {
        this.email = email;
        this.uid = uid;
        this.lastLogin = lastLogin;
        this.invalidAttempts = invalidAttempts;
        this.active = active;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String email, int userID) {
        this.email = email;
        this.userID = userID;
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public int getInvalidAttempts() {
        return invalidAttempts;
    }

    public void setInvalidAttempts(int invalidAttempts) {
        this.invalidAttempts = invalidAttempts;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", email='" + email + '\'' +
                ", uid='" + uid + '\'' +
                ", lastLogin='" + lastLogin + '\'' +
                ", invalidAttempts=" + invalidAttempts +
                ", active='" + active + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
