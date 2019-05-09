package response;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.NoSuchElementException;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Provider
public class NoResultMapper implements ExceptionMapper<NoSuchElementException> {

    @Override
    public Response toResponse(NoSuchElementException e) {
        String message = "{ \"message\": \""+e.getLocalizedMessage()+"\"}";
        return Response.status(404).type(APPLICATION_JSON).entity(message).build();
    }
}
