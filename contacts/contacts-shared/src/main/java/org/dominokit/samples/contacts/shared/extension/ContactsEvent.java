package org.dominokit.samples.contacts.shared.extension;

import org.dominokit.domino.api.shared.extension.ActivationEvent;
import org.dominokit.domino.api.shared.extension.ActivationEventContext;

public class ContactsEvent extends ActivationEvent {
    public ContactsEvent(boolean state) {
        super(state);
    }
}
