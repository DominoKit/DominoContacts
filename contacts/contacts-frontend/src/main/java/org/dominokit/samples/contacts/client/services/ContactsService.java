package org.dominokit.samples.contacts.client.services;

import org.dominokit.domino.api.client.annotations.service.RequestFactory;
import org.dominokit.domino.api.client.request.Response;
import org.dominokit.domino.api.shared.request.ArrayResponse;
import org.dominokit.domino.api.shared.request.VoidResponse;
import org.dominokit.samples.contacts.model.shared.Contact;
import org.dominokit.samples.contacts.model.shared.Group;

import javax.ws.rs.*;

@RequestFactory()
public interface ContactsService {

    @Path(value = "contacts")
    Response<ArrayResponse<Contact>> list();

    @Path(value = "contacts")
    @PUT
    Response<VoidResponse> update(Contact contact);

    @Path(value = "contacts")
    @POST
    Response<VoidResponse> create(Contact contact);

    @Path(value = "contacts/:id")
    @DELETE
    Response<VoidResponse> delete(String id);

    @Path("groups")
    @GET
    Response<ArrayResponse<Group>> listGroups();
}
