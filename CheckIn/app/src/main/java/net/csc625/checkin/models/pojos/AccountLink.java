package net.csc625.checkin.models.pojos;

public class AccountLink {
    private int accountLinkID;
    private String active;
    private int user_id;
    private int student_id;
    private String message;

    public AccountLink(int accountLinkID, String active, int user_id, int student_id, String message) {
        this.accountLinkID = accountLinkID;
        this.active = active;
        this.user_id = user_id;
        this.student_id = student_id;
        this.message = message;
    }

    public int getAccountLinkID() {
        return accountLinkID;
    }

    public void setAccountLinkID(int accountLinkID) {
        this.accountLinkID = accountLinkID;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AccountLink{" +
                "accountLinkID='" + accountLinkID + '\'' +
                ", active='" + active + '\'' +
                ", user_id='" + user_id + '\'' +
                ", student_id='" + student_id + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

}
