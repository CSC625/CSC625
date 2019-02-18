package net.csc625.checkin.models.pojos;

import java.text.DecimalFormat;

/**
 * Created by Kevin Stanley on 2/24/2018.
 */

public class CourseGrade {
    private String grade;
    private int gradeCount;
    private Course course;

    public CourseGrade(String grade, int gradeCount, Course course)
    {
        this.grade = grade;
        this.gradeCount = gradeCount;
        this.course = course;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getGradeCount() {
        return gradeCount;
    }

    public void setGradeCount(int gradeCount) {
        this.gradeCount = gradeCount;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getCalcGrade()
    {
        String formatGrade = "";
        DecimalFormat df = new DecimalFormat("#.00");

        try {
            formatGrade = df.format((Integer.parseInt(grade) * 1.00 / gradeCount) * 100)  + "%";
        } catch(Exception e) { formatGrade = "N/A"; }

        return formatGrade;

    }

    @Override
    public String toString() {
        return "CourseGrade [grade=" + grade + ", gradeCount=" + gradeCount + ", course=" + course + "]";
    }
}
