package org.dominokit.samples.contacts.menu.client.presenters;

import org.dominokit.domino.api.client.annotations.presenter.*;
import org.dominokit.domino.api.client.mvp.presenter.BaseClientPresenter;
import org.dominokit.samples.contacts.layout.shared.extension.LayoutEvent;
import org.dominokit.samples.contacts.layout.shared.extension.UpdateLayoutModelEvent;
import org.dominokit.samples.contacts.layout.shared.model.LayoutModel;
import org.dominokit.samples.contacts.layout.shared.model.MenuEntry;
import org.dominokit.samples.contacts.layout.shared.model.MenuModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@PresenterProxy
@Singleton
@AutoRoute(routeOnce = true)
@DependsOn({@EventsGroup({LayoutEvent.class})})
public class MenuProxy extends BaseClientPresenter{

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuProxy.class);

    @OnInit
    public void onMenuInit() {
        LayoutModel layoutModel = new LayoutModel();
        layoutModel.setTitle("Contacts");

        MenuModel menuModel = new MenuModel();
        menuModel.setTitle("Menu");

        List<MenuEntry> menuEntries = menuModel.getEntries();

        menuEntries
                .add(new MenuEntry("Contacts List", "contacts", "contacts"));

        menuEntries
                .add(new MenuEntry("Contacts groups", "group", "groups"));

        layoutModel.setMenuModel(menuModel);

        fireEvent(UpdateLayoutModelEvent.class, () -> layoutModel);

    }

}