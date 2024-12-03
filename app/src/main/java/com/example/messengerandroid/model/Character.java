package com.example.messengerandroid.model;

import androidx.annotation.NonNull;

import java.util.List;

public class Character {

    private String name;
    private String culture;
    private String born;
    private List<String> titles;
    private List<String> aliases;
    private List<String> playedBy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    public List<String> getPlayedBy() {
        return playedBy;
    }

    public void setPlayedBy(List<String> playedBy) {
        this.playedBy = playedBy;
    }

    @NonNull
    @Override
    public String toString() {
        return "Character{" +
                "name='" + (name != null ? name : "Unknown") + '\'' +
                ", culture='" + (culture != null ? culture : "Unknown") + '\'' +
                ", born='" + (born != null ? born : "Unknown") + '\'' +
                ", titles=" + (titles != null && !titles.isEmpty() ? String.join(", ", titles) : "Unknown") +
                ", aliases=" + (aliases != null && !aliases.isEmpty() ? String.join(", ", aliases) : "Unknown") +
                ", playedBy=" + (playedBy != null && !playedBy.isEmpty() ? String.join(", ", playedBy) : "Unknown") +
                '}';
    }

}
