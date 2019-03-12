package org.dominokit.samples.contacts.repository.server;

import org.dominokit.samples.contacts.model.shared.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

@Path("service/")
public class ContactsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactsController.class);

    @Path("contacts")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public void list(@Suspended final AsyncResponse asyncResponse) {
        ContactsRepository.INSTANCE.list(asyncResponse::resume);
    }

    @Path("contacts/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public void findById(@Suspended final AsyncResponse asyncResponse, @PathParam("id") String id) {
        ContactsRepository.INSTANCE.findById(id, asyncResponse::resume);
    }

    @Path("contacts/{id}")
    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    public void delete(@PathParam("id") String id) {
        ContactsRepository.INSTANCE.delete(id);
    }

    @Path("contacts")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Contact contact) {
        ContactsRepository.INSTANCE.update(contact);
        LOGGER.info("SAVING contact" + contact.toString());
    }
}

