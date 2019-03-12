package org.dominokit.samples.info.shared.extension;

import org.dominokit.domino.api.shared.extension.ActivationEvent;
import org.dominokit.domino.api.shared.extension.ActivationEventContext;

public class InfoEvent extends ActivationEvent {
    public InfoEvent(boolean state) {
        super(state);
    }
}
