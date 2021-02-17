package ru.geekbrains.android.mynotes;

import java.util.GregorianCalendar;

public class MyNote {
    private String title;
    private String describe;
    private final GregorianCalendar create_at;

    public MyNote(String title, String describe) {
        this.title = title;
        this.describe = describe;
        create_at = new GregorianCalendar();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public GregorianCalendar getCreate_at() {
        return create_at;
    }
}
