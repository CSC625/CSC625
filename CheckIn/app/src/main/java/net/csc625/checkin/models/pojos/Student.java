package net.csc625.checkin.models.pojos;

/**
 * Created by David on 11/8/2017.
 */

public class Student
{
    private int studentID;
    private String studentFirstName, studentLastName, studentDateOfBirth;

    public Student(String studentFirstName, String studentLastName, String dateOfBirth, int studentID)
    {
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.studentDateOfBirth = dateOfBirth;
        this.studentID = studentID;
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

    @Override
    public String toString()
    {
        return "Student [studentID = " + studentID + ", firstName = " + studentFirstName+
                ",lastName = " + studentLastName + ",DOB = "+studentDateOfBirth+
                "]";
    }
}
