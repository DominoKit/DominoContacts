package org.dominokit.samples.components.client;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.grid.flex.FlexAlign;
import org.dominokit.domino.ui.grid.flex.FlexItem;
import org.dominokit.domino.ui.grid.flex.FlexJustifyContent;
import org.dominokit.domino.ui.grid.flex.FlexLayout;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.style.Color;
import org.dominokit.domino.ui.utils.BaseDominoElement;

public class SocialMediaComponent extends BaseDominoElement<HTMLDivElement, SocialMediaComponent> {

    private FlexLayout root = FlexLayout.create();

    public SocialMediaComponent() {
        init(this);

        root.css("social-icons")
                .setAlignItems(FlexAlign.CENTER)
                .setJustifyContent(FlexJustifyContent.CENTER)
                .appendChild(FlexItem.create()
                        .appendChild(Icons.ALL.facebook_mdi()
                                .size48()
                                .setColor(Color.INDIGO)))
                .appendChild(FlexItem.create()
                        .appendChild(Icons.ALL.twitter_mdi()
                                .size48()
                                .setColor(Color.LIGHT_BLUE)))
                .appendChild(FlexItem.create()
                        .appendChild(Icons.ALL.linkedin_mdi()
                                .size48()
                                .setColor(Color.BLUE)));
    }

    @Override
    public HTMLDivElement asElement() {
        return root.asElement();
    }
}
