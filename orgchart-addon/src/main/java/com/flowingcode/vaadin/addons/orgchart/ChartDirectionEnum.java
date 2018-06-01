/**
 * 
 */
package com.flowingcode.vaadin.addons.orgchart;

/**
 * @author mlope
 *
 */
public enum ChartDirectionEnum {
	
	TOP_TO_BOTTOM("t2b"), BOTTOM_TO_TOP("b2t"), LEFT_TO_RIGHT("l2r"), RIGHT_TO_LEFT("r2l");
		
	private String abreviation;
	
	ChartDirectionEnum(String abreviation) {
		this.abreviation = abreviation;
	}

	public String getAbreviation() {
		return abreviation;
	}

}
