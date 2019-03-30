package net.csc625.checkin.models.pojos;

public class Student
{
    private int studentID;
    private String studentFirstName, studentLastName, studentDateOfBirth;
    private User user;

    public Student(String studentFirstName, String studentLastName, String dateOfBirth, int studentID, User user)
    {
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.studentDateOfBirth = dateOfBirth;
        this.studentID = studentID;
        this.user = user;
    }

    public Student(String studentFirstName, String studentLastName, String studentDateOfBirth) {
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.studentDateOfBirth = studentDateOfBirth;
    }

    public Student(String studentFirstName, String studentLastName) {
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
    }

    public Student() {

    }


    public int getStudentID() {  return studentID;   }
    public void setStudentID(int studentID) {  this.studentID = studentID;   }

    public String getStudentFirstName() {  return studentFirstName;  }
    public void setStudentFirstName(String studentFirstName)
    {this.studentFirstName = studentFirstName;}

    public String getStudentLastName() {  return studentLastName;  }
    public void setStudentLastName(String studentLastName)
    {this.studentLastName = studentLastName;}

    public String getStudentDateOfBirth() {return studentDateOfBirth;}
    public void setDateOfBirth(String DateOfBirth)
    {this.studentDateOfBirth = DateOfBirth;}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString()
    {
        return "Student [studentID = " + studentID + ", firstName = " + studentFirstName+
                ",lastName = " + studentLastName + ",DOB = "+studentDateOfBirth+
                ",user = "+user+
                "]";
    }
}
