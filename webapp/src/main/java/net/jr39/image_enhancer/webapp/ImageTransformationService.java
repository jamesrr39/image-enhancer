package net.jr39.image_enhancer.webapp;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author james
 */
@Path("/transform")
public interface ImageTransformationService {
	
	@GET
	@Produces("image/jpg")
	public Response transformImage();
	
	
	@POST
	@Produces("image/png")
	//@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response transformPostedImage(String a);
	
}
