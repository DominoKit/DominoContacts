package org.dominokit.samples.contacts.layout.shared.model;


import java.util.ArrayList;
import java.util.List;

public class MenuModel {

    private String title;
    private final List<MenuEntry> entries = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MenuEntry> getEntries() {
        return entries;
    }
}
