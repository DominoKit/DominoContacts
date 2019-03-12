package org.dominokit.samples.contacts.layout.shared.extension;

import org.dominokit.domino.api.shared.extension.ActivationEvent;
import org.dominokit.domino.api.shared.extension.ActivationEventContext;

public class LayoutEvent extends ActivationEvent {
    public LayoutEvent(boolean state) {
        super(state);
    }
}
