package net.jr39.image_enhancer;

import com.google.common.collect.ImmutableList;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import net.jr39.image_enhancer.graphics.transformations.AbstractTransformation;
import net.jr39.image_enhancer.graphics.transformations.BrightenTransformation;
import net.jr39.image_enhancer.graphics.transformations.BrightenTransformationArgs;
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

		images = ImmutableList.copyOf(ImageEnhancerHelper.getImagesFromPaths(appArgs.getImagePaths()));

		if (images.isEmpty()) {
			logger.log(Level.WARNING, "No images loaded, specify image(s) with '--image=' argument(s)");
		}

		images.parallelStream().forEach((Image image) -> {
			boolean lookingForNewFilePath = true;
			int lookingForNewFilePathIteration = 0;
			final String imageFormat = "jpg";
			String processedImageFilePath = null;
			File f;

			AbstractTransformation chosenTransformation = ImageEnhancerHelper.getTransformationType(appArgs.getTransformationType(), appArgs.getTransformationArgs());			
			logger.log(Level.INFO, "performing '{0}' with args: '{1}'", new Object[]{
				chosenTransformation.getClass().getName(), 
				Arrays.toString(appArgs.getTransformationArgs())
			});
			image.performTransformation(chosenTransformation);

			// find a new filename to save this as
			while (lookingForNewFilePath) {
				processedImageFilePath = image.getFilePath() + "_processed_" + lookingForNewFilePathIteration + "." + imageFormat;
				f = new File(processedImageFilePath);
				if (!f.exists()) {
					lookingForNewFilePath = false;
				} else {
					lookingForNewFilePathIteration++;
				}
			}

			try {
				ImageIO.write(image.getLatestImage(), imageFormat, new File(processedImageFilePath));
				logger.log(Level.INFO, "Saved transformed image at: ''{0}''", processedImageFilePath);
			} catch (IOException ex) {
				logger.log(Level.SEVERE, "Couldn't save image: '" + processedImageFilePath + "'", ex);
			}

		});

	}
}
