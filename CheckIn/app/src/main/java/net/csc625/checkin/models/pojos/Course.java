package net.csc625.checkin.models.pojos;

public class Course {

    private int courseId;
    private String name;
    private String year;
    private Teacher teacher;
    private Boolean isSelected = false;

    public Course() {
        this.teacher = new Teacher();
    }

    public Course(int courseId, String name, String year, Teacher teacher) {
        this.courseId = courseId;
        this.name = name;
        this.year = year;
        this.teacher = teacher;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public boolean getIsSelected() {  return isSelected;   }
    public void setIsSelected(boolean isSelected) {  this.isSelected = isSelected;   }

    @Override
    public String toString() {
        return "Course [courseId=" + courseId + ", name=" + name + ", year=" + year + ", teacher=" + teacher + "]";
    }

}
