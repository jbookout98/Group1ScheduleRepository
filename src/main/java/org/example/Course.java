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


    public String getCrn() { return crn; }
    public String getSubject() { return subject; }
    public String getNumber() { return number; }
    public String getSection() { return section; }
    public String getInstructor() { return instructor; }
    public String getDays() { return days; }
    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }
    public String getBuilding() { return building; }
    public String getRoom() { return room; }
    public String getCampusLocation() { return campusLocation; }
    @Override
    public String toString() {
        return "{" +
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