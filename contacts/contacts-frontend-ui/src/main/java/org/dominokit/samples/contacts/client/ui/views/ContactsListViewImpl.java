package org.dominokit.samples.contacts.client.ui.views;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import org.dominokit.domino.api.client.annotations.UiView;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.cards.HeaderAction;
import org.dominokit.domino.ui.dialogs.ConfirmationDialog;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.Row;
import org.dominokit.domino.ui.grid.Row_12;
import org.dominokit.domino.ui.grid.flex.FlexItem;
import org.dominokit.domino.ui.grid.flex.FlexLayout;
import org.dominokit.domino.ui.icons.Icon;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.loaders.Loader;
import org.dominokit.domino.ui.loaders.LoaderEffect;
import org.dominokit.domino.ui.modals.BaseModal;
import org.dominokit.domino.ui.notifications.Notification;
import org.dominokit.domino.ui.style.Color;
import org.dominokit.domino.ui.style.Styles;
import org.dominokit.domino.ui.utils.DominoElement;
import org.dominokit.domino.view.BaseElementView;
import org.dominokit.samples.contacts.client.presenters.ContactsListProxy;
import org.dominokit.samples.contacts.client.views.ContactsListView;
import org.dominokit.samples.contacts.model.shared.Contact;
import org.jboss.gwt.elemento.core.IsElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.img;

@UiView(presentable = ContactsListProxy.class)
public class ContactsListViewImpl extends BaseElementView<HTMLDivElement> implements ContactsListView {

    private DominoElement<HTMLDivElement> element;
    private ContactsUiHandlers uiHandlers;
    private Map<String, Card> contactsCards = new HashMap<>();
    private Loader loader;

    @Override
    public void init(HTMLDivElement root) {
        this.element = DominoElement.of(root);
    }

    @Override
    public HTMLDivElement createRoot() {
        return div().asElement();
    }

    @Override
    public void startLoading() {
        loader = Loader.create(root, LoaderEffect.WIN8_LINEAR)
                .start();
    }

    @Override
    public void update(List<Contact> contacts) {
        contactsCards.clear();
        IntStream.range(0, (contacts.size() + 4 - 1) / 4)
                .mapToObj(i -> contacts.subList(i * 4, Math.min(4 * (i + 1), contacts.size())))
                .map(ContactsRow::new)
                .forEach(contactsRow -> element.appendChild(contactsRow));


        DomGlobal.console.info("Loading completed");
    }

    @Override
    public void confirmAction(String message, ConfirmedHandler confirmedHandler) {
        ConfirmationDialog.create(message)
                .onConfirm(dialog -> {
                    dialog.close();
                    confirmedHandler.onConfirmed();
                })
                .onReject(BaseModal::close)
                .open();
    }

    @Override
    public void onContactDeleted(Contact contact) {
        Notification.createSuccess("Contact [" + contact.getName().asString() + "], have been deleted")
                .show();

        Card card = contactsCards.get(contact.get_id());
        contactsCards.remove(contact.get_id());
        card.remove();
    }

    @Override
    public void setUiHandlers(ContactsUiHandlers uiHandlers) {
        this.uiHandlers = uiHandlers;
    }

    private class ContactsRow implements IsElement {

        private final Row_12 row;

        public ContactsRow(List<Contact> subList) {
            row = Row.create();
            subList.stream()
                    .forEach(contact -> {
                        DomGlobal.console.info(contact.getName().asString() + " : " + contact.isFavourite());
                        Icon favoritIcon = Icons.ALL.star_border();
                        row
                                .appendChild(Column.span2()
                                        .apply(column -> {
                                            if (subList.indexOf(contact) == 0) {
                                                column.offset2();
                                            }
                                        })
                                        .appendChild(Card
                                                .create(contact.getName().asString(), contact.getGroups().size() + " Groups")
                                                .apply(contactCard -> contactsCards.put(contact.get_id(), contactCard))
                                                .addHeaderAction(HeaderAction.create(favoritIcon
                                                        .addClickListener(evt -> {
                                                            DomGlobal.console.info(contact.getName().asString() + " : " + contact.isFavourite());
                                                            uiHandlers.onToggleFavorite(contact, favorite -> {
                                                                if (favorite) {
                                                                    favoritIcon.setColor(Color.ORANGE);
                                                                } else {
                                                                    favoritIcon.setColor(Color.GREY);
                                                                }
                                                                favoritIcon.toggleIcon();
                                                            });
                                                        })
                                                        .setToggleIcon(Icons.ALL.star())
                                                        .setColor(contact.isFavourite() ? Color.ORANGE : Color.GREY)
                                                        .apply(icon -> {
                                                            if (contact.isFavourite()) {
                                                                icon.toggleIcon();
                                                            }
                                                        }))
                                                )
                                                .styler(style -> style.add("contact-card"))
                                                .fitContent()
                                                .setBodyBackground(Color.GREY_LIGHTEN_3)
                                                .appendChild(div()
                                                        .css(Styles.padding_20)
                                                        .add(img(contact.getPicture().getLarge())
                                                                .css(Styles.img_responsive, "contact-photo", Styles.default_shadow))

                                                )
                                                .appendChild(FlexLayout.create()
                                                        .styler(style -> style
                                                                .add(Styles.padding_10)
                                                                .add("contact-card-footer"))
                                                        .appendChild(FlexItem.create()
                                                                .appendChild(Icons.ALL.delete()
                                                                        .setColor(Color.RED_DARKEN_1)
                                                                        .clickable()
                                                                        .addClickListener(evt -> {
                                                                            uiHandlers.onDeleteContact(contact);
                                                                        })
                                                                ))
                                                        .appendChild(FlexItem.create()
                                                                .setFlexGrow(1))
                                                        .appendChild(FlexItem.create()
                                                                .appendChild(Icons.ALL.account_card_details_mdi()
                                                                        .setColor(Color.BLUE_GREY_DARKEN_3)
                                                                        .clickable()
                                                                        .addClickListener(evt -> uiHandlers.onContactSelected(contact))
                                                                ))

                                                )
                                        ));
                    });

            loader.stop();
        }

        @Override
        public HTMLElement asElement() {
            return row.asElement();
        }
    }
}