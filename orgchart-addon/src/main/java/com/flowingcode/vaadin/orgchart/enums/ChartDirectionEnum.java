package com.flowingcode.vaadin.orgchart.enums;


/**
 * Enumeration of the directions that the organization chart can have. <br>
 * Default one is "t2b -> Top to Bottom".
 * 
 * @author pbartolo
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
