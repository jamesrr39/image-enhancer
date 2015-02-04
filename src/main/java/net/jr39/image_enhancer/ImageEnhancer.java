package net.jr39.image_enhancer;

import com.google.common.collect.ImmutableList;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import net.jr39.image_enhancer.graphics.transformations.AbstractTransformation;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

/**
 * Entry point for the command-line app
 *
 * @author james
 */
public class ImageEnhancer {

	private static List<Image> images;
	private static final Logger logger = Logger.getLogger(ImageEnhancer.class.getName());

	public static void main(String[] args) throws IOException, CmdLineException {
		
		AppArgs appArgs = new AppArgs();
		CmdLineParser parser = new CmdLineParser(appArgs);
		parser.parseArgument(args);

		images = appArgs.getImages();

		if (images.isEmpty()) {
			logger.log(Level.WARNING, "No images loaded, specify image(s) with '-image=' argument(s)");
		}

		images.parallelStream().forEach((Image image) -> {
			final String imageFormat = "jpg";

			AbstractTransformation chosenTransformation = appArgs.getTransformationType();
			logger.log(Level.INFO, "performing {0} on pixels: {1}, and shape args: {2}", new Object[]{
				chosenTransformation.getClass().getName(),
				appArgs.getTransformationArgs().getShapeArgs().getPixelsToBeTransformed(),
				appArgs.getTransformationShape()
			});
			image.performTransformation(chosenTransformation);

			// find a new filename to save this as
			final String processedImageFilePath = ImageEnhancerHelper.generateFileName(image.getFilePath());
			ImageEnhancerHelper.saveImageToDisk(image.getLatestImage(), processedImageFilePath, imageFormat);



		});

	}
}
