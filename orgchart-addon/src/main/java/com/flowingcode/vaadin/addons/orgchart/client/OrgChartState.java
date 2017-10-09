package com.flowingcode.vaadin.addons.orgchart.client;

import com.flowingcode.vaadin.addons.orgchart.constants.ChartConstants;
import com.flowingcode.vaadin.addons.orgchart.enums.ChartDirectionEnum;
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
	
	public String chartNodeContent;
	
	public String chartNodeTitle = ChartConstants.CHART_NODE_TITLE_DEFAULT;
	
	public String chartDirection = ChartDirectionEnum.TOP_TO_BOTTOM.getAbreviation(); // default value in the library
	
	public Boolean chartZoom = false;
	
	public Boolean chartPan = false;
	
	public Double chartZoominLimit = ChartConstants.CHART_ZOOM_IN_LIMIT_DEFAULT;
			
	public Double chartZoomoutLimit = ChartConstants.CHART_ZOOM_OUT_LIMIT_DEFAULT;
	
	public Boolean chartExportButton = false;
	
	public String chartExportFileName = ChartConstants.DEFAULT_CHART_EXPORT_FILENAME;
	
	public String chartExportFileExtension = ChartConstants.CHART_EXPORT_EXTENSION_PNG; // default value in the library
	
	public Boolean chartToggleSiblingsResp = false;
	
	public Integer chartDepth;
	
	public Integer chartVerticalDepth;
	
	public boolean chartExpandCollapse = false;
	
}