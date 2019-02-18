package net.csc625.checkin.models.pojos;
import java.sql.Date;

public class Attendance
{
    private int attendanceId;
    private Student student;
    private Course course;
    private String year;
    private String status;
    private String comment;
    private Date date;
    char updateType;

    public Attendance(){}

    public Attendance(int attendanceId, Student student, Course course, String status, String comment, String year, Date date, char updateType)
    {
        this.attendanceId = attendanceId;
        this.student = student;
        this.course = course;
        this.status = status;
        this.comment = comment;
        this.year = year;
        this.date = date;
        this.updateType = updateType;
    }
    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUpdateType(char updateType) {
        this.updateType = updateType;
    }
    public int getAttendanceId() {
        return attendanceId;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public String getStatus() {
        return status;
    }

    public String getComment() {
        return comment;
    }

    public String getYear() {
        return year;
    }

    public Date getDate() {
        return date;
    }

    public char getUpdateType(){
        return updateType;
    }

    @Override
    public String toString()
    {
        return "Attendance [AttendanceID = " +this.attendanceId + "Student = "+ this.student +
                "Course = " + this.course + "Status = " + this.status +
                "Comment = " + this.comment + "Year = " + this.year +
                "Date = " + this.date.toString() + "UpdateType = " + this.updateType + "]";
    }
}
