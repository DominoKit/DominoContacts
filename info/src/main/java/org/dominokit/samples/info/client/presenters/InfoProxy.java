package org.dominokit.samples.info.client.presenters;

import org.dominokit.domino.api.client.annotations.presenter.*;
import org.dominokit.domino.api.client.mvp.presenter.ViewBaseClientPresenter;
import org.dominokit.samples.contacts.layout.shared.extension.LayoutEvent;
import org.dominokit.samples.info.client.services.InfoServiceFactory;
import org.dominokit.samples.info.client.views.InfoView;
import org.dominokit.samples.info.shared.extension.InfoEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PresenterProxy
@AutoRoute(token = "contacts/:contactId")
@Slot("right-panel")
@AutoReveal
@DependsOn(@EventsGroup({LayoutEvent.class}))
@OnStateChanged(InfoEvent.class)
public class InfoProxy extends ViewBaseClientPresenter<InfoView> {

    private static final Logger LOGGER = LoggerFactory.getLogger(InfoProxy.class);
    @PathParameter
    protected String contactId;

    @OnReveal
    public void onInfoRevealed() {
        InfoServiceFactory.INSTANCE
                .getContact(contactId)
                .onSuccess(contact -> view.update(contact))
                .send();
    }

    @OnRemove
    protected void removeToken(){
        history().pushState(history().currentToken().removeLastPath().value());
    }
}