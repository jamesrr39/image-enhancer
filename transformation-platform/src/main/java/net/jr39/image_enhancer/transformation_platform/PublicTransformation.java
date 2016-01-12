package net.jr39.image_enhancer.transformation_platform;


/**
 * Transformations that should be available to end-points (CLI app, HTTP API)
 * @author james
 */
public interface PublicTransformation {
	
	PublicTransformation createInstance();
	
}
