package org.dominokit.samples.contacts.client.presenters;

import org.dominokit.domino.api.client.annotations.presenter.*;
import org.dominokit.domino.api.client.mvp.presenter.ViewBaseClientPresenter;
import org.dominokit.samples.contacts.client.services.ContactsServiceFactory;
import org.dominokit.samples.contacts.client.views.NewContactView;
import org.dominokit.samples.contacts.layout.shared.extension.LayoutEvent;
import org.dominokit.samples.contacts.model.shared.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PresenterProxy
@AutoRoute(token = "contacts/new-contact")
@AutoReveal
@Slot("content")
@DependsOn({@EventsGroup(LayoutEvent.class)})
public class NewContactProxy extends ViewBaseClientPresenter<NewContactView> implements NewContactView.NewContactUiHandler {

    public static final Logger LOGGER = LoggerFactory.getLogger(NewContactProxy.class);

    @OnReveal
    public void initGroups() {
        view.startLoading();
        ContactsServiceFactory.INSTANCE
                .listGroups()
                .onSuccess(response -> view.setGroups(response.asList()))
                .onFailed(failedResponse -> LOGGER.error("Failed to load groups : ", failedResponse.getThrowable()))
                .send();
    }

    @Override
    public void onCreate(Contact contact) {
        ContactsServiceFactory.INSTANCE
                .create(contact)
                .onSuccess(response-> {})
                .onFailed(failedResponse -> LOGGER.error("Failed to create contact : ", failedResponse.getThrowable()))
                .send();
    }
}
