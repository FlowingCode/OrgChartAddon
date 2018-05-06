package com.flowingcode.vaadin.addons.orgchart.client;

import com.flowingcode.vaadin.addons.orgchart.client.constants.ChartConstants;
import com.flowingcode.vaadin.addons.orgchart.client.enums.ChartDirectionEnum;
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
	
	public Boolean chartExpandCollapse = false;
	
	public Boolean chartDraggable = false; //Note from library: this feature doesn't work on IE due to its poor support for HTML5 drag & drop APpublic Boolean chartDiscardDragButton = false;
	
	public String chartNodeId = ChartConstants.CHART_NODE_ID_DEFAULT;  
	
}