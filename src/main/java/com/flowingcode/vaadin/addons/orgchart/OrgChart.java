package com.flowingcode.vaadin.addons.orgchart;

/*-
 * #%L
 * OrgChart Add-on
 * %%
 * Copyright (C) 2017 - 2020 Flowing Code S.A.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flowingcode.vaadin.addons.orgchart.client.OrgChartState;
import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.shared.Registration;

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
//@NpmPackage(value = "orgchart", version = "2.1.4")
@NpmPackage(value = "html2canvas", version = "^0.5.0-beta4")
@NpmPackage(value = "jquery", version = "3.4.1")
@JsModule("jquery/dist/jquery.js")
@JsModule("./orgchart/dist/js/jquery.orgchart.js")
@StyleSheet("context://frontend/jquery.orgchart.min.css")
@Tag("fc-orgchart")
@JsModule("./fc-orgchart.js")
public class OrgChart extends Div {

	private OrgChartItem orgChartItem;
	
	private OrgChartState state =  new OrgChartState();
	
	public OrgChart(OrgChartItem orgChartItem) {
		super();
		this.orgChartItem = orgChartItem;
		this.setValue(orgChartItem);
		String identifier = "orgchart" + this.hashCode();
		this.setId(identifier);
	}
	
	public void initializeChart() {
		String identifier = "orgchart" + this.hashCode();
		this.getElement().executeJs("this.initializeOrgChart($0,$1,$2)", convertToJsonObj(state), state.value,identifier);
	}

	public void setValue(OrgChartItem orgChartItem) {
		String value = convertToJsonObj(orgChartItem);
		getState().value = value;
	}

	protected OrgChartState getState() {
		return this.state;
	}

	private String convertToJsonObj(Object orgChartItem) {
		String result = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orgChartItem);
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

	/**
	 *  orgchart visibleLevel option 
	 */
	public void setChartDepth(Integer chartDepth) {
		getState().chartDepth = chartDepth;
	}

	/**
	 *  orgchart verticalLevel option 
	 */
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

	/**
	 * Fires event on node click.
	 * 
	 * @param nodeId
	 */
	@ClientCallable
	public void onClick(String nodeId) {		
		Integer clickedNodeId = Integer.valueOf(nodeId);
		OrgChartItem clickedItem = getById(clickedNodeId, orgChartItem);
	
		fireNodeClickEvent(clickedItem,true);
	}
	
	/**
	 * Updates chart after drag and drop event.
	 * 
	 * @param draggedNode
	 * @param dragZone
	 * @param dropZone
	 */
	@ClientCallable
	public void updateDraggedNode(String draggedNode, String dragZone, String dropZone) {
	
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
		
		fireDragAndDropEvent(draggedItem, true);		
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
		
	/**
	 * Sets the template generation function used to customize the internal structure of nodes. 
	 * {@code functionBody} is the body of a javascript function that recieves one parameter 
	 * (the JSON datasoure representing a node) and returns an HTML snippet. 
	 * The name of this parameter is given by {@code parameterName}.
	 * 
	 * Example:
	 * <code>
	 * setNodeTemplate("item","return '<span>'+item.name+'</span>';")
	 * </code>
	 * configures the following JS function as node template:
	 * <code>
	 * function(item) { return '<span>'+item.name+'</span>'; }
	 * </code>
	 * 
	 * {@linkplain OrgChartItem#setData(String, String) custom properties} 
	 * are accessible through {@code item.data}
	 * <br>
	 * 
	 * @param parameterName the name of the parameter of a javascript function
	 * @param functionBody the body of a javascript function
	 */
	public void setNodeTemplate(String parameterName, String functionBody) {
		getState().nodeTemplateParam = parameterName;
		getState().nodeTemplate = functionBody;
	}
	
	/**
	 * Adds a {@link DragAndDropListener} to the component.
	 * 
	 * @param dragAndDropListener
	 * 						the listener to be added.
	 */
	public void addDragAndDropListener (ComponentEventListener<DragAndDropEvent> listener) {
		 addListener(DragAndDropEvent.class, listener);
	}

	/**
	 * Fires a {@link DragAndDropEvent}.
	 * 
	 * @param draggedItem
	 * 			the item being dragged.
	 */
	protected void fireDragAndDropEvent(OrgChartItem draggedItem, boolean fromClient) {
        fireEvent(new DragAndDropEvent(this, draggedItem, fromClient));
    }
		
	/**
	 * Event thrown when a node is dragged and dropped. 
	 */
	public static class DragAndDropEvent extends ComponentEvent<OrgChart> {
		
		private final OrgChartItem draggedItem;
						
		public DragAndDropEvent(OrgChart source, OrgChartItem draggedItem, boolean fromClient) {	
			super(source,fromClient);
			this.draggedItem = draggedItem;
		}

		public OrgChartItem getDraggedItem() {
			return draggedItem;
		}
		
		public OrgChart getOrgChart() {
			return (OrgChart)source;
		}
				
	}
	

    public Registration addOnNodeClickListener(
            ComponentEventListener<NodeClickEvent> listener) {
           return addListener(NodeClickEvent.class, listener);
       }

	/**
	 * Fires a {@link NodeClickEvent}.
	 * 
	 * @param clickedItem
	 * 			the item being clicked.
	 */
	protected void fireNodeClickEvent(OrgChartItem clickedItem, boolean fromClient) {
        fireEvent(new NodeClickEvent(this, clickedItem, fromClient));
    }	
	
	/**
	 * Event thrown when a node is clicked. 
	 */
	public static class NodeClickEvent extends ComponentEvent<OrgChart> {
		
		private final OrgChartItem clickedItem;
						
		public NodeClickEvent(OrgChart source, OrgChartItem clickedItem, boolean fromClient) {	
			super(source,fromClient);
			this.clickedItem = clickedItem;
		}

		public OrgChartItem getClickedItem() {
			return clickedItem;
		}
		
		public OrgChart getOrgChart() {
			return (OrgChart)source;
		}
				
	}
	
	/**
	 * Collapses all nodes except the root
	 */
	public void setCollapsedNodes() {
		setChartDepth(1);
	}
}
