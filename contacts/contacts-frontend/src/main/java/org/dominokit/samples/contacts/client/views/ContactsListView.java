package org.dominokit.samples.contacts.client.views;

import org.dominokit.domino.api.client.mvp.view.ContentView;
import org.dominokit.domino.api.client.mvp.view.HasUiHandlers;
import org.dominokit.domino.api.client.mvp.view.UiHandlers;
import org.dominokit.samples.contacts.model.shared.Contact;

import java.util.List;
import java.util.function.Consumer;

public interface ContactsListView extends ContentView, HasUiHandlers<ContactsListView.ContactsUiHandlers> {
    void startLoading();

    void update(List<Contact> contacts);

    void confirmAction(String message, ConfirmedHandler confirmedHandler);

    void onContactDeleted(Contact contact);

    interface ContactsUiHandlers extends UiHandlers{
        void onContactSelected(Contact contact);
        void onDeleteContact(Contact contact);
        void onToggleFavorite(Contact contact, Consumer<Boolean> favoriteConsumer);
    }

    @FunctionalInterface
    interface ConfirmedHandler{
        void onConfirmed();
    }
}