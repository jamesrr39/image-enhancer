
package net.jr39.image_enhancer.graphics.filters;

public class AppliedFilters {

	public final boolean isRedFilterApplied, isGreenFilterApplied, isBlueFilterApplied;
	
	AppliedFilters(boolean isRedFilterApplied, boolean isGreenFilterApplied, boolean isBlueFilterApplied){
		this.isRedFilterApplied = isRedFilterApplied;
		this.isGreenFilterApplied = isGreenFilterApplied;
		this.isBlueFilterApplied = isBlueFilterApplied;
	}
}
