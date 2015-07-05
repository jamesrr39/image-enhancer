package net.jr39.image_enhancer;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
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
		
		appArgs.getImages().parallelStream().forEach((Image image) -> {
			final String imageFormat = "jpg";

			AbstractTransformation chosenTransformation = appArgs.getTransformationType();
			image.performTransformation(chosenTransformation);

			// find a new filename to save this as
			final String processedImageFilePath = ImageEnhancerHelper.generateFileName(image.getFilePath());
			ImageEnhancerHelper.saveImageToDisk(image.getLatestImage(), processedImageFilePath, imageFormat);



		});

	}
}
