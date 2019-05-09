package rest.service;

import data.PigeonDAO;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static model.HalLink.SELF;

abstract class ServiceBase {

    @Context
    protected UriInfo uriInfo;
    protected final PigeonDAO dao = PigeonDAO.getInstance();

    protected Response returnOK(Object entity) {
        return Response.ok(entity).link(uriInfo.getAbsolutePath(), SELF).type(APPLICATION_JSON_TYPE).build();
    }

    protected Response returnNoContent() {
        return Response.noContent().link(uriInfo.getAbsolutePath(), SELF).type(APPLICATION_JSON_TYPE).build();
    }
}
