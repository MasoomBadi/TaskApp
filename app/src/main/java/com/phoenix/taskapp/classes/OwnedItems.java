package com.phoenix.taskapp.classes;

public class OwnedItems {

    String title;
    String educator;
    String courseID;

    public OwnedItems(String title, String educator, String courseID) {
        this.title = title;
        this.educator = educator;
        this.courseID = courseID;
    }

    public String getTitle() {
        return title;
    }

    public String getEducator() {
        return educator;
    }

    public String getCourseID() {
        return courseID;
    }
}
