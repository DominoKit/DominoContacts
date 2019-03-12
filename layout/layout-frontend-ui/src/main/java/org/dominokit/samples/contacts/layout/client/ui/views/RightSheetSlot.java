package org.dominokit.samples.contacts.layout.client.ui.views;

import elemental2.dom.HTMLElement;
import jsinterop.base.Js;
import org.dominokit.domino.api.client.mvp.slots.Slot;
import org.dominokit.domino.api.shared.extension.Content;
import org.dominokit.domino.ui.modals.IsModalDialog;
import org.dominokit.domino.ui.modals.ModalDialog;

import static java.util.Objects.nonNull;
import static org.dominokit.domino.ui.style.Unit.px;

public class RightSheetSlot implements Slot {

    private ModalDialog modalDialog;

    public static RightSheetSlot create() {
        return new RightSheetSlot();
    }

    public RightSheetSlot() {
        modalDialog = ModalDialog.create()
                .setType(IsModalDialog.ModalType.RIGHT_SHEET)
                .hideHeader()
                .hideFooter()
                .apply(element -> {
                    element.getBodyElement()
                            .styler(style -> style.setPadding(px.of(0)));
                    element.getHeaderContainerElement().style().setPadding(px.of(5));
                })
                .onClose(() -> modalDialog.getBodyElement().clearElement());
    }

    @Override
    public void cleanUp() {
        if (nonNull(modalDialog)) {
            modalDialog.remove();
        }
    }

    @Override
    public void updateContent(Content content) {
        modalDialog.getBodyElement()
                .clearElement();

        modalDialog.appendChild(Js.<HTMLElement>uncheckedCast(content.get()));
        if (!modalDialog.isOpen()) {
            modalDialog.open();
        }
    }
}
