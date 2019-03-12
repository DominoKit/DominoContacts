package org.dominokit.samples.contacts.layout.client.presenters;

import org.dominokit.domino.api.client.annotations.presenter.*;
import org.dominokit.domino.api.client.mvp.presenter.ViewBaseClientPresenter;
import org.dominokit.samples.contacts.layout.client.views.LayoutView;
import org.dominokit.samples.contacts.layout.shared.extension.LayoutEvent;
import org.dominokit.samples.contacts.layout.shared.extension.UpdateLayoutModelEvent;
import org.dominokit.samples.contacts.layout.shared.model.LayoutModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.dominokit.domino.api.client.mvp.presenter.ViewBaseClientPresenter.DOCUMENT_BODY;

@PresenterProxy
@Singleton
@AutoRoute(routeOnce = true)
@Slot(DOCUMENT_BODY)
@AutoReveal
@OnStateChanged(LayoutEvent.class)
public class LayoutProxy extends ViewBaseClientPresenter<LayoutView> implements LayoutView.LayoutUiHandlers {

    private static final Logger LOGGER = LoggerFactory.getLogger(LayoutProxy.class);

    @ListenTo(event = UpdateLayoutModelEvent.class)
    public void onUpdateModelEventReceived(LayoutModel model){
        view.updateLayout(model);
    }

    @Override
    public void onMenuSelected(String token) {
        history().fireState(token);
    }
}