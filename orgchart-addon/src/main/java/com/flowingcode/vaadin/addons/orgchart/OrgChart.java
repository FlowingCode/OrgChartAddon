package com.flowingcode.vaadin.addons.orgchart;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flowingcode.vaadin.addons.orgchart.client.OrgChartServerRpc;
import com.flowingcode.vaadin.addons.orgchart.client.OrgChartState;
import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.AbstractJavaScriptComponent;

/**
 * OrgChart component definition. <br>
 * Uses JQuery library <b>OrgChart</b> to show an organization chart. <br>
 * More information about this library at <a href=
 * "https://github.com/dabeng/OrgChart">https://github.com/dabeng/OrgChart</a>
 * 
 * @author pbartolo
 *
 */
@SuppressWarnings("serial")
@JavaScript({ "jquery-3.2.1.min.js", "html2canvas.js", "jquery.orgchart.js", "orgchart-connector.js" })
@StyleSheet({ "jquery.orgchart.css", "orgchart.css", "font-awesome.css" })
public class OrgChart extends AbstractJavaScriptComponent {

	private OrgChartItem orgChartItem;
	
	public OrgChart(OrgChartItem orgChartItem) {
		super();
		this.orgChartItem = orgChartItem;
		this.setValue(orgChartItem);
		registerRpc(new OrgChartServerRpc() {

			@Override
			public void updateChart(String draggedNode, String dragZone, String dropZone) {
				updateDraggedNode(draggedNode, dragZone, dropZone);
			}
		
		});
	}

	public void setValue(OrgChartItem orgChartItem) {
		String value = convertToJsonObj(orgChartItem);
		getState().value = value;
	}

	@Override
	protected OrgChartState getState() {
		return (OrgChartState) super.getState();
	}

	private String convertToJsonObj(OrgChartItem orgChartItem) {
		String result = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orgChartItem);
			// System.out.println(result);
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

	public void setChartDraggable(Boolean chartDraggable) {
		getState().chartDraggable = chartDraggable;
	}
	
	/**
	 * Returns latest value of the orgchart element.
	 */
	public OrgChartItem getOrgChartItem() {
		return orgChartItem;
	}

	private void updateDraggedNode(String draggedNode, String dragZone, String dropZone) {
	
		Integer draggedNodeId = Integer.valueOf(draggedNode);

		OrgChartItem draggedItem = getById(draggedNodeId, orgChartItem);
		OrgChartItem oldParentItem = getById(Integer.valueOf(dragZone), orgChartItem);
		OrgChartItem newParentItem = getById(Integer.valueOf(dropZone), orgChartItem);

		// update old parent (remove item)
		List<OrgChartItem> oldParentUpdated = oldParentItem.getChildren().stream()
				.filter(i -> !draggedNodeId.equals(i.getId())).collect(Collectors.toList());
		oldParentItem.setChildren(oldParentUpdated);

		// update new parent (add item)
		newParentItem.addChildren(draggedItem);
	}

	private OrgChartItem getById(Integer id, OrgChartItem item) {
		if (id.equals(item.getId())) {
			return item;
		} else {
			return getChildItemById(id, item.getChildren());
		}
	}

	private OrgChartItem getChildItemById(Integer id, List<OrgChartItem> children) {
		OrgChartItem result = null;
		for (int i = 0; i < children.size() && result == null; i++) {
			result = getById(id, children.get(i));
		}
		return result;
	}
		
}
