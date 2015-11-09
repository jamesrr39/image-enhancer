package net.jr39.image_enhancer.webapp;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.ws.rs.core.Response;
import net.jr39.image_enhancer.transformation_platform.graphics.transformations.GenericTransformationArgs;
import net.jr39.image_enhancer.transformation_platform.graphics.transformations.SepiaTransformation;

/**
 *
 * @author james
 */
public class ImageTransformationServiceImpl implements ImageTransformationService {

	@Override
	public Response transformImage() {
		Color startColour = Color.MAGENTA;

		final int IMAGE_WIDTH = 200;
		final int IMAGE_HEIGHT = 100;
		final int startColourInt = startColour.getRGB();

		BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
		int[] rgbArray = new int[image.getWidth() * image.getHeight()];
		for (int i = 0; i < rgbArray.length; i++) {
			rgbArray[i] = startColourInt;
		}
		image.setRGB(25, 50, image.getWidth() - 25, image.getHeight() - 50, rgbArray, 0, image.getWidth() - 25);

		SepiaTransformation transformation = new SepiaTransformation(new GenericTransformationArgs(null));
		BufferedImage transformedImage = transformation.transform(image);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(transformedImage, "jpeg", baos);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		byte[] imageData = baos.toByteArray();

		return Response.ok(imageData).build();
	}

	@Override
	public Response transformPostedImage(String base64Image) {
		String encodedImage = base64Image.substring(base64Image.indexOf(",") + 1);
		Base64.Decoder decoder = Base64.getDecoder();
		Base64.Encoder encoder = Base64.getEncoder();
		byte[] bs = decoder.decode(encodedImage);
		InputStream in = new ByteArrayInputStream(bs);
		SepiaTransformation transformation = new SepiaTransformation(new GenericTransformationArgs(null));
		
		try {
			BufferedImage bImageFromConvert = ImageIO.read(in);
			BufferedImage transformedImage = transformation.transform(bImageFromConvert);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(transformedImage, "png", baos);
			return buildCORSResponse(Response.ok(encoder.encode(baos.toByteArray())));
		} catch (IOException ex) {
			String errorMessage = "Couldn't convert base 64 string to image";
			Logger.getLogger(ImageTransformationServiceImpl.class.getName()).log(Level.SEVERE, errorMessage, ex);
			return buildCORSResponse(Response.status(Response.Status.BAD_REQUEST).entity(errorMessage));
		}
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