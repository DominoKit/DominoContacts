package org.dominokit.samples.info.client.views.ui;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.api.client.annotations.UiView;
import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.style.Styles;
import org.dominokit.domino.ui.utils.DominoElement;
import org.dominokit.domino.view.BaseElementView;
import org.dominokit.samples.components.client.ContactInfoComponent;
import org.dominokit.samples.contacts.model.shared.Contact;
import org.dominokit.samples.info.client.presenters.InfoProxy_Presenter;
import org.dominokit.samples.info.client.views.InfoView;

import static org.jboss.gwt.elemento.core.Elements.div;

@UiView(presentable = InfoProxy_Presenter.class)
public class InfoViewImpl extends BaseElementView<HTMLDivElement> implements InfoView {


    private final ContactInfoComponent infoComponent = new ContactInfoComponent();

    @Override
    public void init(HTMLDivElement root) {

        DominoElement.of(root)
                .css(Styles.align_center)
                .appendChild(infoComponent)
                .appendChild(Button.create(Icons.ALL.edit())
                        .setContent("Edit")
                        .linkify()
                        .styler(style -> style.add(Styles.m_t_20)));
    }

    @Override
    public HTMLDivElement createRoot() {
        return div().asElement();
    }

    @Override
    public void update(Contact contact) {
        infoComponent.edit(contact);
    }
}