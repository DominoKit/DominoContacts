package org.dominokit.samples.contacts.layout.shared.model;

import org.dominokit.domino.api.shared.extension.EventContext;

public class LayoutModel implements EventContext {

    private String title;
    private MenuModel menuModel;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MenuModel getMenuModel() {
        return menuModel;
    }

    public void setMenuModel(MenuModel menuModel) {
        this.menuModel = menuModel;
    }
}
