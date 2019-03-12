package org.dominokit.samples.contacts.layout.client.views;

import org.dominokit.domino.api.client.mvp.view.ContentView;
import org.dominokit.domino.api.client.mvp.view.HasUiHandlers;
import org.dominokit.domino.api.client.mvp.view.UiHandlers;
import org.dominokit.samples.contacts.layout.shared.model.LayoutModel;

public interface LayoutView extends ContentView, HasUiHandlers<LayoutView.LayoutUiHandlers> {
    void updateLayout(LayoutModel model);

    interface LayoutUiHandlers extends UiHandlers{
        void onMenuSelected(String token);
    }
}