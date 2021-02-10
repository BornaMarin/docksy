package com.marin.docksy;

public class MetaResponse {

    public String title;
    public String authorDescription;
    public String footer;
    public String author;
    public Boolean theme;
    public String primaryLight;
    public String primaryDark;
    public String secondaryLight;
    public String secondaryDark;

    public MetaResponse(String title, String authorDescription, String footer, String author, Boolean theme, String primaryLight, String primaryDark, String secondaryLight, String secondaryDark) {
        this.title = title;
        this.authorDescription = authorDescription;
        this.footer = footer;
        this.author = author;
        this.theme = theme;
        this.primaryLight = primaryLight;
        this.primaryDark = primaryDark;
        this.secondaryLight = secondaryLight;
        this.secondaryDark = secondaryDark;
    }
}
