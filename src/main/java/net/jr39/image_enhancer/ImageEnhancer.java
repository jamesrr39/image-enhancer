package net.jr39.image_enhancer;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import net.jr39.image_enhancer.graphics.transformations.AbstractTransformation;
import net.jr39.image_enhancer.graphics.transformations.BrightenTransformation;
import net.jr39.image_enhancer.graphics.transformations.BrightenTransformationArgs;

/**
 * Entry point for the command-line app
 * @author james
 */
public class ImageEnhancer {

	private static final List<Image> images = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		final String imagePathArgKey = "--image=";
		java.util.Arrays.asList(args)
				.stream()
				.filter(arg -> arg.trim().indexOf(imagePathArgKey) == 0)
				.map(arg -> arg.substring(imagePathArgKey.length()))
				.forEach((String imagePath) -> {
					try {
						images.add(new Image(imagePath));
					} catch (IOException ex) {
						Logger.getLogger(ImageEnhancer.class.getName()).log(Level.SEVERE, "Couldn't load image: '" + imagePath + "'", ex);
					}
				});

		if (images.isEmpty()) {
			Logger.getLogger(ImageEnhancer.class.getName()).log(Level.WARNING, "No images loaded, specify image(s) with '--image=' argument(s)");
		}

		images.parallelStream().forEach((Image image) -> {
			boolean lookingForNewFilePath = true;
			int lookingForNewFilePathIteration = 0;
			final String imageFormat = "jpg";
			String processedImageFilePath = null;
			File f;

			// mock (todo)
			BrightenTransformationArgs transformationArgs = new BrightenTransformationArgs(2, new Point(1000, 1000), new Rectangle(400, 400));
			AbstractTransformation chosenTransformation = new BrightenTransformation(transformationArgs);
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
				Logger.getLogger(ImageEnhancer.class.getName()).log(Level.INFO, "Saved transformed image at: ''{0}''", processedImageFilePath);
			} catch (IOException ex) {
				Logger.getLogger(ImageEnhancer.class.getName()).log(Level.SEVERE, "Couldn't save image: '" + processedImageFilePath + "'", ex);
			}

		});

	}
}
