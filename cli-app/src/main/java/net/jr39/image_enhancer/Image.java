package net.jr39.image_enhancer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import net.jr39.image_enhancer.transformation_platform.graphics.transformations.AbstractTransformation;

/**
 *
 * @author james
 */
public class Image {

	private final String filePath;
	private final List<BufferedImage> imageStages = new LinkedList<>();

	public Image(String filePath) throws IOException {
		this.filePath = filePath;
		this.imageStages.add(ImageIO.read(new File(filePath)));
	}

	public int getNumberOfStages() {
		return this.imageStages.size();
	}

	public BufferedImage getImageAtStage(int stage) {
		return imageStages.get(stage);
	}

	public List<BufferedImage> getImageStages() {
		return this.imageStages;
	}

	public String getFilePath() {
		return filePath;
	}

	public BufferedImage getLatestImage() {
		return imageStages.get(imageStages.size() - 1);
	}

	public BufferedImage performTransformation(AbstractTransformation transformation) {
		return performTransformation(transformation, imageStages.size() - 1);
	}

	public BufferedImage performTransformation(AbstractTransformation transformation, int fromImageAtStage) {
		BufferedImage transformedImage = transformation.transform(imageStages.get(fromImageAtStage));
		imageStages.add(transformedImage);
		return transformedImage;
	}

}
