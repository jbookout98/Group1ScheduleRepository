package org.example;

public class Course {
    String crn;
    String subject;
    String number;
    String section;
    String instructor;
    String days;
    String startTime;
    String endTime;
    String building;
    String room;

    public Course(String crn, String subject, String number, String section, String instructor, String days,
                  String startTime, String endTime, String building, String room) {
        this.crn = crn;
        this.subject = subject;
        this.number = number;
        this.section = section;
        this.instructor = instructor;
        this.days = days;
        this.startTime = startTime;
        this.endTime = endTime;
        this.building = building;
        this.room = room;
    }

    @Override
    public String toString() {
        return "Course{" +
                "CRN='" + crn + '\'' +
                ", Subject='" + subject + '\'' +
                ", Number='" + number + '\'' +
                ", Section='" + section + '\'' +
                ", Instructor='" + instructor + '\'' +
                ", Days='" + days + '\'' +
                ", Start Time='" + startTime + '\'' +
                ", End Time='" + endTime + '\'' +
                ", Building='" + building + '\'' +
                ", Room='" + room + '\'' +
                '}';
    }
}