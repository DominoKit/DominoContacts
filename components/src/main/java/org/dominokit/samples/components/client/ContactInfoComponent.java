package org.dominokit.samples.components.client;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLHeadingElement;
import org.dominokit.domino.ui.Typography.Paragraph;
import org.dominokit.domino.ui.chips.Chip;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.style.Color;
import org.dominokit.domino.ui.style.ColorScheme;
import org.dominokit.domino.ui.style.Styles;
import org.dominokit.domino.ui.themes.Theme;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.DominoElement;
import org.dominokit.samples.contacts.model.shared.Contact;

import static org.jboss.gwt.elemento.core.Elements.h;
import static org.jboss.gwt.elemento.core.Elements.img;

public class ContactInfoComponent extends BaseDominoElement<HTMLDivElement, ContactInfoComponent> {

    private DominoElement<HTMLDivElement> root = DominoElement.div();

    private DominoElement<HTMLHeadingElement> nameElement = DominoElement.of(h(2));
    private DominoElement<HTMLDivElement> pictureElement = DominoElement.div()
            .css(Color.GREY_LIGHTEN_3.getBackground(), "miniinfo-picture");
    private DominoElement<HTMLDivElement> groupsContainer = DominoElement.div().css("info-groups");
    private DominoElement<HTMLDivElement> infoFields = DominoElement.div().css("info-fields");
    private DominoElement<HTMLHeadingElement> emailText = DominoElement.of(h(4));
    private DominoElement<HTMLHeadingElement> phoneText = DominoElement.of(h(4));
    private SocialMediaComponent socialMediaIcons = new SocialMediaComponent();

    public ContactInfoComponent() {
        init(this);
        root
                .css(Styles.align_center)
                .appendChild(nameElement)
                .appendChild(pictureElement)
                .appendChild(groupsContainer)
                .appendChild(socialMediaIcons)
                .appendChild(infoFields
                        .appendChild(Icons.ALL.email_mdi()
                                .setColor(ColorScheme.GREY.lighten_2()))
                        .appendChild(emailText)
                        .appendChild(Icons.ALL.phone_mdi()
                                .setColor(ColorScheme.GREY.lighten_2()))
                        .appendChild(phoneText)
                )
                .appendChild(DominoElement.div().css("bio")
                        .css(Styles.align_center, Styles.padding_20)
                        .appendChild(h(4).textContent("BIO"))
                        .appendChild(Paragraph.create("Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget"))
                );


    }

    @Override
    public HTMLDivElement asElement() {
        return root.asElement();
    }

    public void edit(Contact contact) {
        nameElement.setTextContent(contact.getName().asString());
        updatePicture(contact);

        updateGroups(contact);

        emailText.setTextContent(contact.getEmail());
        phoneText.setTextContent(contact.getPhone());
    }

    private void updatePicture(Contact contact) {
        pictureElement
                .clearElement()
                .appendChild(img(contact.getPicture().getLarge())
                        .css(Styles.img_responsive, "contact-photo", Styles.default_shadow))
                .apply(pictureElement -> {
                    if (contact.isFavourite()) {
                        pictureElement.appendChild(Icons.ALL.star_mdi()
                                .size36()
                                .setColor(Color.ORANGE)
                                .styler(style -> style.add("fav-icon"))
                        );
                    }
                });
    }

    private void updateGroups(Contact contact) {
        groupsContainer
                .clearElement()
                .apply(container -> {
                    contact.getGroups()
                            .forEach(group -> container.appendChild(Chip.create(group)
                                    .setColorScheme(Theme.currentTheme.getScheme())));
                });
    }
}
