package com.phoenix.taskapp.classes;

import java.util.List;

public class FilterObject {
    List<String> ownedIds;
    List<String> skills;
    List<String> curriculum;
    List<String> styles;
    List<String> series;
    String educator;
    String id;
    String title;

    public FilterObject(List<String> ownedIds, List<String> skills, List<String> curriculum, List<String> styles, List<String> series, String educator, String id, String title) {
        this.ownedIds = ownedIds;
        this.skills = skills;
        this.curriculum = curriculum;
        this.styles = styles;
        this.series = series;
        this.educator = educator;
        this.id = id;
        this.title = title;
    }

    public List<String> getOwnedIds() {
        return ownedIds;
    }

    public List<String> getSkills() {
        return skills;
    }

    public List<String> getCurriculum() {
        return curriculum;
    }

    public List<String> getStyles() {
        return styles;
    }

    public List<String> getSeries() {
        return series;
    }

    public String getEducator() {
        return educator;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
