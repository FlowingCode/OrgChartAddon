package com.flowingcode.vaadin.addons.orgchart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.ui.Tag;
import com.vaadin.ui.common.HtmlImport;
import com.vaadin.ui.polymertemplate.PolymerTemplate;

@SuppressWarnings("serial")
@Tag("org-chart-container")
@HtmlImport("frontend://orgchart/orgchart.html")
public class OrgChart extends PolymerTemplate<OrgChartModel> {
	
	private OrgChartItem parentItem;
	
	public OrgChart(OrgChartItem parentItem) {
		setParentItem(parentItem);
	}
	
    private String convertToJsonObj(OrgChartItem orgChartLevelItem) {    	   	    	
    	String result = null;
    	ObjectMapper mapper = new ObjectMapper();
    	try {
			result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orgChartLevelItem);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}    	    
	    return result;
    }
    
    public void setParentItem(OrgChartItem parentItem) {
		this.parentItem = parentItem;
		getModel().setOrgChartStructure(convertToJsonObj(parentItem));
    }
    
    public OrgChartItem getParentItem() {
    	return this.parentItem;
    }
    	    
}
