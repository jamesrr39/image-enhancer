
package net.jr39.image_enhancer.transformation_platform.graphics;

public class ColourRange {
	
	private final int minRed, maxRed, minGreen, maxGreen, minBlue, maxBlue;
	
	public ColourRange(int minRed, int maxRed, int minGreen, int maxGreen, int minBlue, int maxBlue){
		
		this.minRed = minRed;
		this.maxRed = maxRed;
		this.minGreen = minGreen;
		this.maxGreen = maxGreen;
		this.minBlue = minBlue;
		this.maxBlue = maxBlue;
		
	}
	
	public int getMinRed(){
		return this.minRed;
	}
	
	public int getMaxRed(){
		return this.maxRed;
	}
	
	public int getMinGreen(){
		return this.minGreen;
	}
	
	public int getMaxGreen(){
		return this.maxGreen;
	}
	
	public int getMinBlue(){
		return this.minBlue;
	}
	
	public int getMaxBlue(){
		return this.maxBlue;
	}

}
