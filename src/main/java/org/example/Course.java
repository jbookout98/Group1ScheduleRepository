package org.example;

public class Course {
    private String crn;
    private String subject;
    private String department;
    private String number;
    private String section;
    private String instructor;
    private String days;
    private String startTime;
    private String endTime;
    private String building;
    private String room;
    private String campus;
    private String classType;
    private String method;
    private String dpOrIn;
    private String pot;
    private int actual;
    private int max;

    /**
     * Default constructor for the {@link Course} class.
     */
    public Course() {}

    /**
     * Constructs a {@link Course} object with the specified attributes.
     *
     * @param crn        the course registration number
     * @param subject    the subject of the course
     * @param department the department offering the course
     * @param number     the course number
     * @param section    the section of the course
     * @param instructor the instructor of the course
     * @param days       the days the course is scheduled
     * @param startTime  the start time of the course
     * @param endTime    the end time of the course
     * @param building   the building where the course is held
     * @param room       the room where the course is held
     * @param campus     the campus where the course is offered
     * @param classType  the type of the class (e.g., lecture, lab)
     * @param method     the method of instruction (e.g., in-person, online)
     * @param dpOrIn     additional course information (e.g., department/program indicator)
     * @param pot        the part of term information for the course
     * @param actual     the current number of enrolled students
     * @param max        the maximum number of students allowed
     */
    public Course(String crn, String subject, String department, String number, String section,
                  String instructor, String days, String startTime, String endTime,
                  String building, String room, String campus, String classType,
                  String method, String dpOrIn, String pot, int actual, int max) {
        this.crn = crn;
        this.subject = subject;
        this.department = department;
        this.number = number;
        this.section = section;
        this.instructor = instructor;
        this.days = days;
        this.startTime = startTime;
        this.endTime = endTime;
        this.building = building;
        this.room = room;
        this.campus = campus;
        this.classType = classType;
        this.method = method;
        this.dpOrIn = dpOrIn;
        this.pot = pot;
        this.actual = actual;
        this.max = max;
    }

    // Getters and Setters
    /**
     * Retrieves the course registration number (CRN).
     *
     * @return the CRN of the course
     */
    public String getCrn() {
        return crn;
    }

    /**
     * Retrieves the subject of the course.
     *
     * @return the subject of the course
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Retrieves the department offering the course.
     *
     * @return the department of the course
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Retrieves the course number.
     *
     * @return the course number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Retrieves the section of the course.
     *
     * @return the course section
     */
    public String getSection() {
        return section;
    }

    /**
     * Retrieves the instructor of the course.
     *
     * @return the instructor of the course
     */
    public String getInstructor() {
        return instructor;
    }

    /**
     * Retrieves the days the course is scheduled.
     *
     * @return the scheduled days for the course
     */
    public String getDays() {
        return days;
    }

    /**
     * Retrieves the start time of the course.
     *
     * @return the start time of the course
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Retrieves the end time of the course.
     *
     * @return the end time of the course
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Retrieves the building where the course is held.
     *
     * @return the building of the course
     */
    public String getBuilding() {
        return building;
    }

    /**
     * Retrieves the room where the course is held.
     *
     * @return the room of the course
     */
    public String getRoom() {
        return room;
    }

    /**
     * Retrieves the campus where the course is offered.
     *
     * @return the campus of the course
     */
    public String getCampus() {
        return campus;
    }

    /**
     * Retrieves the type of the class (e.g., lecture, lab).
     *
     * @return the class type
     */
    public String getClassType() {
        return classType;
    }

    /**
     * Retrieves the method of instruction (e.g., in-person, online).
     *
     * @return the method of instruction
     */
    public String getMethod() {
        return method;
    }

    /**
     * Retrieves additional course information (e.g., department/program indicator).
     *
     * @return the department or program indicator
     */
    public String getDpOrIn() {
        return dpOrIn;
    }

    /**
     * Retrieves the part of term information for the course.
     *
     * @return the part of term information
     */
    public String getPot() {
        return pot;
    }

    /**
     * Retrieves the current number of enrolled students.
     *
     * @return the current number of students
     */
    public int getActual() {
        return actual;
    }

    /**
     * Retrieves the maximum number of students allowed.
     *
     * @return the maximum number of students
     */
    public int getMax() {
        return max;
    }

    /**
     * Updates the course registration number (CRN).
     *
     * @param crn the new CRN for the course
     */
    public void setCrn(String crn) {
        this.crn = crn;
    }

    /**
     * Updates the subject of the course.
     *
     * @param subject the new subject for the course
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Updates the department offering the course.
     *
     * @param department the new department for the course
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Updates the course number.
     *
     * @param number the new course number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Updates the section of the course.
     *
     * @param section the new section for the course
     */
    public void setSection(String section) {
        this.section = section;
    }

    /**
     * Updates the instructor of the course.
     *
     * @param instructor the new instructor for the course
     */
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    /**
     * Updates the days the course is scheduled.
     *
     * @param days the new scheduled days for the course
     */
    public void setDays(String days) {
        this.days = days;
    }

    /**
     * Updates the start time of the course.
     *
     * @param startTime the new start time for the course
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Updates the end time of the course.
     *
     * @param endTime the new end time for the course
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * Updates the building where the course is held.
     *
     * @param building the new building for the course
     */
    public void setBuilding(String building) {
        this.building = building;
    }

    /**
     * Updates the room where the course is held.
     *
     * @param room the new room for the course
     */
    public void setRoom(String room) {
        this.room = room;
    }

    /**
     * Updates the campus where the course is offered.
     *
     * @param campus the new campus for the course
     */
    public void setCampus(String campus) {
        this.campus = campus;
    }

    /**
     * Updates the type of the class (e.g., lecture, lab).
     *
     * @param classType the new class type
     */
    public void setClassType(String classType) {
        this.classType = classType;
    }

    /**
     * Updates the method of instruction (e.g., in-person, online).
     *
     * @param method the new method of instruction
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * Updates additional course information (e.g., department/program indicator).
     *
     * @param dpOrIn the new department or program indicator
     */
    public void setDpOrIn(String dpOrIn) {
        this.dpOrIn = dpOrIn;
    }

    /**
     * Updates the part of term information for the course.
     *
     * @param pot the new part of term information
     */
    public void setPot(String pot) {
        this.pot = pot;
    }

    /**
     * Updates the current number of enrolled students.
     *
     * @param actual the new number of enrolled students
     */
    public void setActual(int actual) {
        this.actual = actual;
    }

    /**
     * Updates the maximum number of students allowed.
     *
     * @param max the new maximum number of students
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Returns a string representation of the course, including all attributes.
     *
     * @return a string representation of the course
     */
    @Override
    public String toString() {
        return "Course{" +
                "crn='" + crn + '\'' +
                ", subject='" + subject + '\'' +
                ", department='" + department + '\'' +
                ", number='" + number + '\'' +
                ", section='" + section + '\'' +
                ", instructor='" + instructor + '\'' +
                ", days='" + days + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", building='" + building + '\'' +
                ", room='" + room + '\'' +
                ", campus='" + campus + '\'' +
                ", classType='" + classType + '\'' +
                ", method='" + method + '\'' +
                ", dpOrIn='" + dpOrIn + '\'' +
                ", pot='" + pot + '\'' +
                ", actual=" + actual +
                ", max=" + max +
                '}';
    }
}