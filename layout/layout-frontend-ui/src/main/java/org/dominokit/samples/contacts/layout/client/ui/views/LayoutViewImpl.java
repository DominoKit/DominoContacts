package org.dominokit.samples.contacts.layout.client.ui.views;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import org.dominokit.domino.api.client.annotations.UiView;
import org.dominokit.domino.api.client.mvp.slots.SlotsEntries;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.cards.HeaderAction;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.layout.Layout;
import org.dominokit.domino.ui.style.ColorScheme;
import org.dominokit.domino.ui.themes.Theme;
import org.dominokit.domino.ui.tree.Tree;
import org.dominokit.domino.ui.tree.TreeItem;
import org.dominokit.domino.view.BaseElementView;
import org.dominokit.domino.view.slots.SingleElementSlot;
import org.dominokit.samples.contacts.layout.client.presenters.LayoutProxy_Presenter;
import org.dominokit.samples.contacts.layout.client.views.LayoutView;
import org.dominokit.samples.contacts.layout.shared.model.LayoutModel;
import org.dominokit.samples.contacts.layout.shared.model.MenuEntry;
import org.dominokit.samples.contacts.layout.shared.model.MenuModel;

import static java.util.Objects.nonNull;
import static org.dominokit.domino.ui.style.Unit.px;

@UiView(presentable = LayoutProxy_Presenter.class)
public class LayoutViewImpl extends BaseElementView<HTMLDivElement> implements LayoutView {

    private Layout layout;
    private Tree menu;
    private LayoutUiHandlers uiHandlers;

    @Override
    public void init(HTMLDivElement root) {
        layout.fixLeftPanelPosition();
        layout.getRightPanel()
                .appendChild(Card.create("Contact details")
                        .addHeaderAction(HeaderAction.create(Icons.HARDWARE_ICONS.keyboard_tab()
                                .clickable()
                                .addClickListener(evt -> layout.hideRightPanel())
                        ))
                        .fitContent()
                        .styler(style -> style.setMarginBottom(px.of(0))));
    }

    @Override
    public void updateLayout(LayoutModel model) {
        layout.setTitle(model.getTitle());
        if (nonNull(menu)) {
            menu.remove();
        }

        MenuModel menuModel = model.getMenuModel();
        if (nonNull(menuModel)) {
            menu = Tree.create(menuModel.getTitle());

            menuModel.getEntries()
                    .forEach(menuEntry -> menu.appendChild(createMenuItem(menuEntry)));
        }

        layout.getLeftPanel().appendChild(menu);
        layout.autoFixLeftPanel();

        new Theme(ColorScheme.TEAL).apply();

    }

    private TreeItem createMenuItem(MenuEntry menuEntry) {
        TreeItem treeItem;
        if (nonNull(menuEntry.getIcon())) {
            treeItem = TreeItem.create(menuEntry.getTitle(), Icons.of(menuEntry.getIcon()));
        } else {
            treeItem = TreeItem.create(menuEntry.getTitle());
        }
        return treeItem
                .addClickListener(evt -> this.uiHandlers.onMenuSelected(menuEntry.getToken()));
    }

    @Override
    public void setUiHandlers(LayoutUiHandlers uiHandlers) {
        this.uiHandlers = uiHandlers;
    }

    @Override
    public SlotsEntries getSlots() {
        return SlotsEntries.create()
                .add("content", SingleElementSlot.of(layout.getContentPanel()))
                .add("right-panel", RightSheetSlot.create());
    }

    @Override
    public HTMLDivElement createRoot() {
        layout = Layout.create();
        return layout.asElement();
    }
}