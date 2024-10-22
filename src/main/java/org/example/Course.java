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
    String campusLocation;
    public Course(String crn, String subject, String number, String section, String instructor, String days,
                  String startTime, String endTime, String building, String room,String location) {
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
        this.campusLocation=location;
    }
    public void setCampusLocation(String location){
        this.campusLocation=location;
    }
    public String getCrn(){
        return  crn;

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
                ", Campus='"+campusLocation+"\'"+
                '}';
    }
}