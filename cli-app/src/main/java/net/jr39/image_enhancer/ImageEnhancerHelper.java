package net.jr39.image_enhancer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author james
 */
public class ImageEnhancerHelper {

	private static final Logger LOGGER = Logger.getLogger(ImageEnhancerHelper.class.getName());

	public static List<Image> getImagesFromPaths(List<String> paths) {
		List<Image> images = new ArrayList<>();
		paths.stream().forEach((String path) -> {
			try {
				images.add(new Image(path));
			} catch (IOException ex) {
				Logger.getLogger(ImageEnhancerHelper.class.getName()).log(Level.SEVERE, "Couldn't load image: '{0}'", path);
			}
		});
		return images;
	}

	public static String generateFileName(final String originalImageFilePath) {

		boolean lookingForNewFilePath = true;
		int lookingForNewFilePathIteration = 0;
		final String imageFormat = "jpg";
		String processedImageFilePath = null;
		File f;

		while (lookingForNewFilePath) {
			processedImageFilePath = originalImageFilePath + "_processed_" + lookingForNewFilePathIteration + "." + imageFormat;
			f = new File(processedImageFilePath);
			if (!f.exists()) {
				lookingForNewFilePath = false;
			} else {
				lookingForNewFilePathIteration++;
			}
		}
		return processedImageFilePath;
	}

	public static void saveImageToDisk(BufferedImage image, String processedImageFilePath, String imageFormat) {
		try {
			ImageIO.write(image, imageFormat, new File(processedImageFilePath));
			LOGGER.log(Level.INFO, "Saved transformed image at: ''{0}''", processedImageFilePath);
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, "Couldn't save image: '" + processedImageFilePath + "'", ex);
		}
	}

}
