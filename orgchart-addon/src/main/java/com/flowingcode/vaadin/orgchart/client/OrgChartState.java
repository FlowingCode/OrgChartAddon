package com.flowingcode.vaadin.orgchart.client;

import com.flowingcode.vaadin.orgchart.constants.ChartConstants;
import com.flowingcode.vaadin.orgchart.enums.ChartDirectionEnum;
import com.vaadin.shared.ui.JavaScriptComponentState;

/**
 * 
 * @author pbartolo
 *
 */
@SuppressWarnings("serial")
public class OrgChartState extends JavaScriptComponentState {

	public String value;
	
	public String chartTitle;
	
	public String chartDirection = ChartDirectionEnum.TOP_TO_BOTTOM.getAbreviation(); // default value in the library
	
	public Boolean chartZoom = false;
	
	public Boolean chartExportButton = false;
	
	public String chartExportFileName = ChartConstants.DEFAULT_CHART_EXPORT_FILENAME;
	
	public String chartExportFileExtension = ChartConstants.CHART_EXPORT_EXTENSION_PNG; // default value in the library
	
}