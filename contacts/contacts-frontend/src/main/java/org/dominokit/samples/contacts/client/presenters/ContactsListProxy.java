package org.dominokit.samples.contacts.client.presenters;

import org.dominokit.domino.api.client.annotations.presenter.*;
import org.dominokit.domino.api.client.mvp.presenter.ViewBaseClientPresenter;
import org.dominokit.domino.api.shared.history.TokenFilter;
import org.dominokit.domino.api.shared.history.TokenParameter;
import org.dominokit.samples.contacts.client.services.ContactsServiceFactory;
import org.dominokit.samples.contacts.client.views.ContactsListView;
import org.dominokit.samples.contacts.layout.shared.extension.LayoutEvent;
import org.dominokit.samples.contacts.model.shared.Contact;
import org.dominokit.samples.contacts.shared.extension.ContactsEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.function.Consumer;

@PresenterProxy
@AutoRoute(token = "contacts", reRouteActivated = false)
@Slot("content")
@AutoReveal
@OnStateChanged(ContactsEvent.class)
@DependsOn({@EventsGroup(LayoutEvent.class)})
public class ContactsListProxy extends ViewBaseClientPresenter<ContactsListView> implements ContactsListView.ContactsUiHandlers {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactsListProxy.class);

    @RoutingTokenFilter
    public static TokenFilter startsWith(String token) {
        return TokenFilter.startsWith(token);
    }

    @OnInit
    public void onContactsInit() {
        LOGGER.info("Contacts initialized");
    }

    @OnReveal
    public void onContactsRevealed() {
        ContactsServiceFactory.INSTANCE
                .list()
                .onBeforeSend(() -> view.startLoading())
                .onSuccess(response -> view.update(Arrays.asList(response.getItems())))
                .onFailed(failedResponse -> LOGGER.error("failed to list contacts", failedResponse.getThrowable()))
                .send();
    }

    @Override
    public void onContactSelected(Contact contact) {
        history().fireState("contacts/:id", TokenParameter.of("id", contact.get_id()));
    }

    @Override
    public void onDeleteContact(Contact contact) {
        LOGGER.info("deleting contact");
        view.confirmAction("Are you sure you want to delete contact [" + contact.getName().asString() + "]", () -> ContactsServiceFactory.INSTANCE.delete(contact.get_id())
                .onSuccess(response -> view.onContactDeleted(contact))
                .send()
        );
    }

    @Override
    public void onToggleFavorite(Contact contact, Consumer<Boolean> favoriteConsumer) {
        contact.setFavourite(!contact.isFavourite());
        ContactsServiceFactory.INSTANCE
                .update(contact)
                .onSuccess(response -> favoriteConsumer.accept(contact.isFavourite())).send();
    }
}