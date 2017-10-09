package com.flowingcode.vaadin.orgchart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flowingcode.vaadin.orgchart.client.OrgChartState;
import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.AbstractJavaScriptComponent;

/**
 * OrgChart component definition. <br> 
 * Uses JQuery library <b>OrgChart</b> to show an organization chart. <br>
 * More information about this library at <a href="https://github.com/dabeng/OrgChart">https://github.com/dabeng/OrgChart</a>
 *  
 * @author pbartolo
 *
 */
@SuppressWarnings("serial")
@JavaScript({"jquery-3.2.1.min.js", "html2canvas.js", "jquery.orgchart.js", "orgchart-connector.js"})
@StyleSheet({"jquery.orgchart.css", "orgchart.css"})
public class OrgChart extends AbstractJavaScriptComponent {
	
	public OrgChart(OrgChartItem orgChartLevelItem) {
		super();
		this.setValue(orgChartLevelItem);
	}
	
	public void setValue(OrgChartItem orgChartLevelItem) {	
		String value = convertToJsonObj(orgChartLevelItem);
		getState().value = value;
	}
	
    @Override
    protected OrgChartState getState() {
        return (OrgChartState) super.getState();
    }	
  
    private String convertToJsonObj(OrgChartItem orgChartLevelItem) {    	   	    	
    	String result = null;
    	ObjectMapper mapper = new ObjectMapper();
    	try {
			result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orgChartLevelItem);
//			System.out.println(result);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}    	    
	    return result;
    }
    
    public void setChartNodeTitle(String chartNodeTitle) {
    	getState().chartNodeTitle = chartNodeTitle;
    }
    
    public void setChartNodeContent(String chartNodeContent) {
    	getState().chartNodeContent = chartNodeContent;
    }
    
    public void setChartDirection(String chartDirection) {
    	getState().chartDirection = chartDirection;
    }
    
    public void setChartTitle(String chartTitle) {
    	getState().chartTitle = chartTitle;
    }
    
    public void setChartZoom(Boolean chartZoom) {
    	getState().chartZoom = chartZoom;
    }
    
    public void setChartExportButton(Boolean chartExportButton) {
    	getState().chartExportButton = chartExportButton;
    }
    
    public void setChartExportFileName(String chartExportFileName) {
    	getState().chartExportFileName = chartExportFileName;
    }
    
    public void setChartExportFileExtension(String chartExportFileExtension) {
    	getState().chartExportFileExtension = chartExportFileExtension;
    }
    
    public void setChartPan(Boolean chartPan) {
    	getState().chartPan = chartPan;
    }
    
    public void setChartZoominLimit(Double chartZoominLimit) {
    	getState().chartZoominLimit = chartZoominLimit;
    }
    
    public void setChartZoomoutLimit(Double chartZoomoutLimit) {
    	getState().chartZoomoutLimit = chartZoomoutLimit;
    }
    
    public void setChartDepth(Integer chartDepth) {
    	getState().chartDepth = chartDepth;
    }
    
    public void setChartVerticalDepth(Integer chartVerticalDepth) {
    	getState().chartVerticalDepth = chartVerticalDepth;
    }
    
    public void setChartToggleSiblingsResp(Boolean chartToggleSiblingsResp) {
    	getState().chartToggleSiblingsResp = chartToggleSiblingsResp;
    }
     
    public void setChartExpandCollapse(Boolean chartExpandCollapse) {
    	getState().chartExpandCollapse = chartExpandCollapse;
    }
}
