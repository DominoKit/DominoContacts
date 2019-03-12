package org.dominokit.samples.contacts.layout.shared.model;

public class MenuEntry {

    private String title;
    private String icon;
    private String token;

    public MenuEntry() {
    }

    public MenuEntry(String title, String icon, String token) {
        this.title = title;
        this.icon = icon;
        this.token = token;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
