package net.jr39.image_enhancer.webapp;

import com.google.common.collect.ImmutableMap;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.ws.rs.core.Response;
import net.jr39.image_enhancer.transformation_platform.graphics.transformations.AbstractTransformation;
import net.jr39.image_enhancer.transformation_platform.graphics.transformations.GenericTransformationArgs;
import net.jr39.image_enhancer.transformation_platform.graphics.transformations.SepiaTransformation;

/**
 *
 * @author james
 */
public class ImageTransformationServiceImpl implements ImageTransformationService {
	
	private final Base64.Decoder decoder = Base64.getDecoder();
	private final Base64.Encoder encoder = Base64.getEncoder();
	
	private static final ImmutableMap<String, Class<? extends AbstractTransformation>> TRANSFORMATIONS = 
			ImmutableMap.<String, Class<? extends AbstractTransformation>> builder()
		.put("sepia", SepiaTransformation.class)
		.build();
	
	@Override
	public Response transformPostedImage(String imageAsBase64, String transformationName) {
		String prefix = imageAsBase64.substring(0, imageAsBase64.indexOf(",") + 1);
		String encodedImage = imageAsBase64.substring(imageAsBase64.indexOf(",") + 1);
		byte[] bs = decoder.decode(encodedImage);
		InputStream in = new ByteArrayInputStream(bs);
		
		Class<? extends AbstractTransformation> clazz = TRANSFORMATIONS.get(transformationName);
		if(null == clazz){
			return Response.status(Response.Status.NOT_FOUND)
					.entity(transformationName + " transformation not found").build();
		}
		
		try {
			AbstractTransformation transformation = clazz.getConstructor(GenericTransformationArgs.class)
					.newInstance(new GenericTransformationArgs(null));
			BufferedImage bImageFromConvert = ImageIO.read(in);
			BufferedImage transformedImage = transformation.transform(bImageFromConvert);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(transformedImage, "png", baos);
			return buildCORSResponse(Response.ok(prefix + encoder.encodeToString(baos.toByteArray())));
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException ex) {
			Logger.getLogger(ImageTransformationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
		}  catch (IOException ex) {
			String errorMessage = "Couldn't convert base 64 string to image";
			Logger.getLogger(ImageTransformationServiceImpl.class.getName()).log(Level.SEVERE, errorMessage, ex);
			return buildCORSResponse(Response.status(Response.Status.BAD_REQUEST).entity(errorMessage));
		}
		return Response.status(500).build();
	}
	
	/**
	 * build a Response with CORS "allow everything" headers
	 * @param responseBuilder
	 * @return response with CORS "allow everything" headers
	 */
	private static Response buildCORSResponse(Response.ResponseBuilder responseBuilder){
		return responseBuilder
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS")
				.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
				.build();
	}

}
