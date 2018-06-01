package com.flowingcode.vaadin.addons.orgchart;

import com.vaadin.flow.templatemodel.TemplateModel;

/**
 * @author mlopez
 *
 */
public interface OrgChartModel extends TemplateModel {

	/**
	 * @param orgChartStructure
	 */
	void setOrgChartStructure(String orgChartStructure);

	/**
	 * @param title
	 */
	void setChartTitle(String title);

	/**
	 * @param nodeContent
	 */
	void setChartNodeContent(String nodeContent);

	/**
	 * @param title
	 */
	void setChartNodeTitle(String title);

	/**
	 * @param direction
	 */
	void setChartDirection(String direction);

	/**
	 * @param zoom
	 */
	void setChartZoom(Boolean zoom);

	/**
	 * @param pan
	 */
	void setChartPan(Boolean pan);

	/**
	 * @param limit
	 */
	void setChartZoominLimit(Double limit);

	/**
	 * @param limit
	 */
	void setChartZoomoutLimit(Double limit);

	/**
	 * @param export
	 */
	void setChartExportButton(Boolean export);

	/**
	 * @param filename
	 */
	void setChartExportFileName(String filename);

	/**
	 * @param extension
	 */
	void setChartExportFileExtension(String extension);

	/**
	 * @param toggle
	 */
	void setChartToggleSiblingsResp(Boolean toggle);

	/**
	 * @param depth
	 */
	void setChartDepth(Integer depth);

	/**
	 * @param depth
	 */
	void setChartVerticalDepth(Integer depth);

	/**
	 * @param value
	 */
	void setChartExpandCollapse(Boolean value);
}
