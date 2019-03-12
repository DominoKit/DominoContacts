package org.dominokit.samples.contacts.client.views;

import org.dominokit.domino.api.client.mvp.view.ContentView;
import org.dominokit.domino.api.client.mvp.view.UiHandlers;
import org.dominokit.samples.contacts.model.shared.Contact;
import org.dominokit.samples.contacts.model.shared.Group;

import java.util.List;

public interface NewContactView extends ContentView {

    void startLoading();

    void setGroups(List<Group> groups);

    interface NewContactUiHandler extends UiHandlers{
        void onCreate(Contact contact);
    }
}
