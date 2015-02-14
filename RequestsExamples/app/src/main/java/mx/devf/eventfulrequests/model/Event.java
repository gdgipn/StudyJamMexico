package mx.devf.eventfulrequests.model;


import java.util.Date;

public class Event {
    String title;
    String description;
    String date;

    public Event(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }
}
