package org.dominokit.samples.info.client.services;

import org.dominokit.domino.api.client.annotations.service.RequestFactory;
import org.dominokit.domino.api.client.request.Response;
import org.dominokit.samples.contacts.model.shared.Contact;
import org.dominokit.samples.info.shared.response.InfoResponse;
import org.dominokit.samples.info.shared.request.InfoRequest;

import javax.ws.rs.Path;

@RequestFactory
public interface InfoService {

    @Path(value = "contacts/:contactId")
    Response<Contact> getContact(String contactId);
}
