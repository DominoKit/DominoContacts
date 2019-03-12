package org.dominokit.samples.info.shared.request;

import org.dominokit.domino.api.shared.request.RequestBean;
import org.dominokit.jacksonapt.annotation.JSONMapper;

@JSONMapper
public class InfoRequest implements RequestBean {

    private String message;

    public InfoRequest() {
    }

    public InfoRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
