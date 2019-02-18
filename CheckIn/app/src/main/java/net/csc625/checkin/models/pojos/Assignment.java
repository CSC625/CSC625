package net.csc625.checkin.models.pojos;

/**
 * Created by Kevin Stanley on 2/6/2018.
 */

public class Assignment {
    private int assignmentId;
    private String year;
    private String name;
    private String type;
    private String maxPoints;
    private Course course;
    private Boolean isSelected = false;
    private String description;

    public Assignment()
    {
        this.course = new Course();
    }

    public Assignment(int assignmentId, String year, String name, String type, String maxPoints, Course course, String description)
    {
        this.assignmentId = assignmentId;
        this.year = year;
        this.name = name;
        this.type = type;
        this.maxPoints = maxPoints;
        this.course = course;
        this.description = description;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(String maxPoints) {
        this.maxPoints = maxPoints;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean selected) {
        isSelected = selected;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Assignment [assignmentId=" + assignmentId + ", year=" + year + ", name=" + name + ", type=" + type + ", maxPoints=" + maxPoints + ", course=" + course.toString() + "], description=" + description + "";
    }
}
