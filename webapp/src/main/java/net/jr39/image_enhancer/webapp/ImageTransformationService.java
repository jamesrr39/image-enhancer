package net.jr39.image_enhancer.webapp;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author james
 */
@Path("/transform")
public interface ImageTransformationService {
	
	@POST
	@Path("{transformationName}")
	@Consumes(MediaType.TEXT_PLAIN)
	public Response transformPostedImage(String imageAsBase64, @PathParam("transformationName") String transformationName);
	
}
