package com.flowingcode.vaadin.addons.orgchart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;

@SuppressWarnings("serial")
@Tag("org-chart-container")
@HtmlImport("frontend://orgchart/orgchart.html")
public class OrgChart extends PolymerTemplate<OrgChartModel> {
	
	private OrgChartItem parentItem;
	
	public OrgChart(OrgChartItem parentItem) {
		setParentItem(parentItem);
		
		// defaults
		getModel().setChartNodeTitle(ChartConstants.CHART_NODE_TITLE_DEFAULT);
		getModel().setChartDirection(ChartDirectionEnum.TOP_TO_BOTTOM.getAbreviation());
		getModel().setChartZoom(false);
		getModel().setChartPan(false);
		getModel().setChartZoominLimit(ChartConstants.CHART_ZOOM_IN_LIMIT_DEFAULT);
		getModel().setChartZoomoutLimit(ChartConstants.CHART_ZOOM_OUT_LIMIT_DEFAULT);
		getModel().setChartExportButton(false);
		getModel().setChartExportFileName(ChartConstants.DEFAULT_CHART_EXPORT_FILENAME);
		getModel().setChartExportFileExtension(ChartConstants.CHART_EXPORT_EXTENSION_PNG);
		getModel().setChartToggleSiblingsResp(false);
		getModel().setChartExpandCollapse(false);

		
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

	public void setChartTitle(String string) {
		getModel().setChartTitle(string);
	}

	public void setChartNodeContent(String string) {
		getModel().setChartNodeContent(string);
	}

	public void setChartExportButton(boolean b) {
		getModel().setChartExportButton(b);
	}

	public void setChartExpandCollapse(boolean b) {
		getModel().setChartExpandCollapse(b);
	}

	public void setChartDirection(String abreviation) {
		getModel().setChartDirection(abreviation);
	}
    	    
}
