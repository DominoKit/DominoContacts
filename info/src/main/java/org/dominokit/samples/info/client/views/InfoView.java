package org.dominokit.samples.info.client.views;

import org.dominokit.domino.api.client.mvp.view.ContentView;
import org.dominokit.samples.contacts.model.shared.Contact;

public interface InfoView extends ContentView{
    void update(Contact contact);
}