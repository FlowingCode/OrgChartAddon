package com.flowingcode.vaadin.addons.orgchart;

import com.vaadin.flow.model.TemplateModel;

/**
 * @author mlopez
 *
 */
public interface OrgChartModel extends TemplateModel {

	/**
	 * @param orgChartStructure
	 */
	void setOrgChartStructure(String orgChartStructure);
	
}
