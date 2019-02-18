package net.csc625.checkin.models.pojos;

/**
 * Created by Kevin Stanley on 1/28/2018.
 */

public class EnrollRecord
{
    private int studentID;
    private int courseID;

    public EnrollRecord(int studentID, int courseID)
    {
        this.studentID = studentID;
        this.courseID = courseID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}
