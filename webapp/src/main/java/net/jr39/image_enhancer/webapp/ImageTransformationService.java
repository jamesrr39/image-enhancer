package net.jr39.image_enhancer.webapp;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author james
 */
@Path("/transform")
public interface ImageTransformationService {
	
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response transformPostedImage(String imageAsBase64);
	
}
