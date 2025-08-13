/*-
 * #%L
 * OrgChart Add-on
 * %%
 * Copyright (C) 2017 - 2025 Flowing Code S.A.
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
package com.flowingcode.vaadin.addons.orgchart;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flowingcode.vaadin.addons.orgchart.client.OrgChartState;
import com.flowingcode.vaadin.addons.orgchart.event.ChildrenAddedEvent;
import com.flowingcode.vaadin.addons.orgchart.event.NodeUpdatedEvent;
import com.flowingcode.vaadin.addons.orgchart.event.NodesRemovedEvent;
import com.flowingcode.vaadin.addons.orgchart.event.ParentAddedEvent;
import com.flowingcode.vaadin.addons.orgchart.event.SiblingsAddedEvent;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.shared.Registration;
import elemental.json.JsonArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * OrgChart component definition. <br>
 * Uses JQuery library <b>OrgChart</b> to show an organization chart. <br>
 * More information about this library at
 * <a href= "https://github.com/dabeng/OrgChart">https://github.com/dabeng/OrgChart</a>
 *
 * @author pbartolo
 */
@SuppressWarnings("serial")
@NpmPackage(value = "orgchart", version = "3.7.0")
@NpmPackage(value = "html2canvas", version = "1.4.1")
@NpmPackage(value = "jquery", version = "3.6.2")
@JsModule("jquery/dist/jquery.js")
@JsModule("orgchart/dist/js/jquery.orgchart.js")
@CssImport("orgchart/dist/css/jquery.orgchart.min.css")
@Tag("fc-orgchart")
@JsModule("./fc-orgchart.js")
@CssImport("./fc-orgchart-styles.css")
@NpmPackage(value = "json-digger", version = "2.0.2")
public class OrgChart extends Div {

  private OrgChartItem orgChartItem;

  private OrgChartState state = new OrgChartState();

  public OrgChart(OrgChartItem orgChartItem) {
    super();
    this.orgChartItem = orgChartItem;
    this.setValue(orgChartItem);
    String identifier = "orgchart" + this.hashCode();
    this.setId(identifier);
  }

  @Override
  protected void onAttach(AttachEvent attachEvent) {
    super.onAttach(attachEvent);
    this.getElement().executeJs("this.initializeOrgChart($0,$1,$2)", convertToJsonObj(state),
        state.value, this.getId().get());
  }

  /**
   * @deprecated This method is no longer needed. Initialization is done in
   *             {@link #onAttach(AttachEvent)}.
   */
  @Deprecated
  public void initializeChart() {}

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

  /** orgchart visibleLevel option */
  public void setChartDepth(Integer chartDepth) {
    getState().chartDepth = chartDepth;
  }

  /** orgchart verticalLevel option */
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

  /** Returns latest value of the orgchart element. */
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

    fireNodeClickEvent(clickedItem, true);
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
   * Sets the template generation function used to customize the internal structure of nodes. {@code
   * functionBody} is the body of a javascript function that recieves one parameter (the JSON
   * datasoure representing a node) and returns an HTML snippet. The name of this parameter is given
   * by {@code parameterName}.
   *
   * <p>
   * Example: <code>
   * setNodeTemplate("item","return '<span>'+item.name+'</span>';")
   * </code> configures the following JS function as node template: <code>
   * function(item) { return '<span>'+item.name+'</span>'; }
   * </code> {@linkplain OrgChartItem#setData(String, String) custom properties} are accessible
   * through {@code item.data} <br>
   *
   * @param parameterName the name of the parameter of a javascript function
   * @param functionBody the body of a javascript function
   */
  public void setNodeTemplate(String parameterName, String functionBody) {
    getState().nodeTemplateParam = parameterName;
    getState().nodeTemplate = functionBody;
  }

  /**
   * Adds a {@link DragAndDropEvent} listener to the component.
   *
   * @param listener the listener to be added.
   */
  public void addDragAndDropListener(ComponentEventListener<DragAndDropEvent> listener) {
    addListener(DragAndDropEvent.class, listener);
  }

  /**
   * Fires a {@link DragAndDropEvent}.
   *
   * @param draggedItem the item being dragged.
   */
  protected void fireDragAndDropEvent(OrgChartItem draggedItem, boolean fromClient) {
    fireEvent(new DragAndDropEvent(this, draggedItem, fromClient));
  }

  /** Event thrown when a node is dragged and dropped. */
  public static class DragAndDropEvent extends ComponentEvent<OrgChart> {

    private final OrgChartItem draggedItem;

    public DragAndDropEvent(OrgChart source, OrgChartItem draggedItem, boolean fromClient) {
      super(source, fromClient);
      this.draggedItem = draggedItem;
    }

    public OrgChartItem getDraggedItem() {
      return draggedItem;
    }

    public OrgChart getOrgChart() {
      return (OrgChart) source;
    }
  }

  public Registration addOnNodeClickListener(ComponentEventListener<NodeClickEvent> listener) {
    return addListener(NodeClickEvent.class, listener);
  }

  /**
   * Fires a {@link NodeClickEvent}.
   *
   * @param clickedItem the item being clicked.
   */
  protected void fireNodeClickEvent(OrgChartItem clickedItem, boolean fromClient) {
    fireEvent(new NodeClickEvent(this, clickedItem, fromClient));
  }

  /** Event thrown when a node is clicked. */
  public static class NodeClickEvent extends ComponentEvent<OrgChart> {

    private final OrgChartItem clickedItem;

    public NodeClickEvent(OrgChart source, OrgChartItem clickedItem, boolean fromClient) {
      super(source, fromClient);
      this.clickedItem = clickedItem;
    }

    public OrgChartItem getClickedItem() {
      return clickedItem;
    }

    public OrgChart getOrgChart() {
      return (OrgChart) source;
    }
  }

  /** Collapses all nodes except the root */
  public void setCollapsedNodes() {
    setChartDepth(1);
  }

  /**
   * Finds the parent of a node with the given ID in the org chart. <br>
   * This method is used to find the parent of a node after the chart has been initialized.
   * 
   * @param childId the ID of the child node whose parent is to be found
   * @param root the root of the org chart from which to start searching
   * @return the parent {@link OrgChartItem} of the node with the given ID, or {@code null} if not
   *         found
   */
  private OrgChartItem findParent(Integer childId, OrgChartItem root) {
    if (root.getChildren() != null) {
      for (OrgChartItem child : root.getChildren()) {
        if (childId.equals(child.getId())) {
          return root;
        }
        OrgChartItem parent = findParent(childId, child);
        if (parent != null) {
          return parent;
        }
      }
    }
    return null;
  }

  /**
   * Converts a JsonArray of IDs to a List of Integers.
   * 
   * @param jsonIds array of numeric IDs
   * @return list of converted integer IDs
   */
  private List<Integer> convertJsonArrayToIntegerList(JsonArray jsonIds) {
    List<Integer> idList = new ArrayList<>();
    for (int i = 0; i < jsonIds.length(); i++) {
      idList.add((int) jsonIds.getNumber(i));
    }
    return idList;
  }

  /**
   * Appends a list of items to a parent node's children list.
   * 
   * @param parentNode the node to which children will be added
   * @param itemsToAdd the list of items to add
   */
  private void appendItemsToParent(OrgChartItem parentNode, List<OrgChartItem> itemsToAdd) {
    if (parentNode != null) {
      List<OrgChartItem> currentChildren = parentNode.getChildren();
      if (currentChildren == null) {
        currentChildren = new ArrayList<>();
      }
      currentChildren.addAll(itemsToAdd);
      parentNode.setChildren(currentChildren);
    }
  }

  /**
   * Adds one or more sibling nodes to a target node in the chart.
   * <p>
   * This method inserts the new items at the same level as the target node, under the same parent.
   * It updates both the internal data structure and the client-side visual representation.
   *
   * @param nodeId the ID of the existing node that will serve as the anchor for adding siblings.
   *        This must not be the root node's ID.
   * @param siblings a list of {@link OrgChartItem} objects to be added as siblings
   * @throws IllegalArgumentException if the {@code nodeId} belongs to the root node of the chart,
   *         as the root cannot have siblings or if the {@code nodeId} is not found in the chart
   */
  public void addSiblings(Integer nodeId, List<OrgChartItem> siblings) {
    // First check if selected node is not the root node
    if (nodeId.equals(this.orgChartItem.getId())) {
      throw new IllegalArgumentException("Cannot add siblings to the root node.");
    }
    // Update the internal data structure
    OrgChartItem targetNode = getById(nodeId, orgChartItem);
    if (targetNode != null) {
      // Find parent of the target node
      OrgChartItem parentNode = findParent(nodeId, orgChartItem);
      if (parentNode != null) {
        // Update parent's children list with the new siblings
        appendItemsToParent(parentNode, siblings);
      }
    } else {
      throw new IllegalArgumentException("Node not found: " + nodeId);
    }

    // Update the visual representation by calling the client-side method addSiblings
    String siblingsJson = convertToJsonObj(siblings);
    this.getElement().executeJs("this.addSiblings($0, $1)", nodeId, siblingsJson);
  }

  /**
   * Handles sibling addition events from the client side. Converts the received JsonArray of
   * sibling IDs to a List and fires a {@link SiblingsAddedEvent}.
   *
   * @param nodeId the ID of the node that received new siblings
   * @param siblingIds array of IDs for the newly added siblings
   */
  @ClientCallable
  private void onSiblingsAdded(String nodeId, JsonArray siblingIds) {
    // Find the node where siblings were added
    OrgChartItem targetItem = getById(Integer.valueOf(nodeId), orgChartItem);
    if (targetItem != null) {

      // Convert the JsonArray to a simple list of integer IDs
      List<Integer> newSiblingIdList = convertJsonArrayToIntegerList(siblingIds);

      // Convert the list of IDs into a list of the actual OrgChartItem objects
      List<OrgChartItem> newSiblingItems =
          newSiblingIdList.stream().map(id -> getById(id, orgChartItem)).filter(Objects::nonNull)
              .collect(Collectors.toList());

      // Fire the event with the parent and the fully populated list of child items
      fireSiblingsAddedEvent(targetItem, newSiblingItems, true);
    }
  }

  /**
   * Adds a listener for sibling addition events. The listener will be notified when new siblings
   * are added to any node in the chart.
   *
   * @param listener the listener to be added
   * @return a {@link Registration} for removing the listener
   */
  public Registration addSiblingsAddedListener(ComponentEventListener<SiblingsAddedEvent> listener) {
    return addListener(SiblingsAddedEvent.class, listener);
  }

  /**
   * Fires a siblings added event.
   *
   * @param item the node that received new siblings
   * @param newSiblings list of the newly added siblings
   * @param fromClient whether the event originated from the client side
   */
  protected void fireSiblingsAddedEvent(OrgChartItem item, List<OrgChartItem> newSiblings,
      boolean fromClient) {
    fireEvent(new SiblingsAddedEvent(this, item, newSiblings, fromClient));
  }

  /**
   * Adds one or more child nodes to a specified parent node in the chart.
   * <p>
   * This method updates both the internal data model and the client-side visuals. Note the specific
   * client-side behavior: if the parent node has no existing children, this uses the library's
   * {@code addChildren} function. If the parent already has children, it uses the
   * {@code addSiblings} function on the first existing child to append the new nodes.
   *
   * @param nodeId the ID of the parent node to which the new children will be added
   * @param children a list of {@link OrgChartItem} objects to be added as new children
   * @throws IllegalArgumentException if the {@code nodeId} is not found in the chart
   */
  public void addChildren(Integer nodeId, List<OrgChartItem> children) {
    // Update the internal data structure
    OrgChartItem targetNode = getById(nodeId, orgChartItem);
    if (targetNode == null) {
      throw new IllegalArgumentException("Node not found: " + nodeId);
    }
    boolean currentChildrenEmpty =
        targetNode.getChildren() == null || targetNode.getChildren().isEmpty();
    // Add new children while preserving existing ones
    appendItemsToParent(targetNode, children);

    // Update the visual representation
    String itemsJson = convertToJsonObj(children);
    if (currentChildrenEmpty) {
      this.getElement().executeJs("this.addChildren($0, $1)", nodeId, itemsJson);
    } else {
      this.getElement().executeJs("this.addSiblings($0, $1)",
          targetNode.getChildren().get(0).getId(), itemsJson);
    }
  }

  @ClientCallable
  private void onChildrenAdded(String nodeId, JsonArray childIds) {
    // Find the parent node where children were added
    OrgChartItem parentItem = getById(Integer.valueOf(nodeId), orgChartItem);
    if (parentItem != null) {

      // Convert the JsonArray to a simple list of integer IDs
      List<Integer> newChildIdList = convertJsonArrayToIntegerList(childIds);

      // Convert the list of IDs into a list of the actual OrgChartItem objects
      List<OrgChartItem> newChildItems =
          newChildIdList.stream().map(id -> getById(id, orgChartItem)).filter(Objects::nonNull)
              .collect(Collectors.toList());

      // Fire the event with the parent and the fully populated list of child items
      fireChildrenAddedEvent(parentItem, newChildItems, true);
    }
  }

  /**
   * Adds a listener for child addition events. The listener will be notified when new children are
   * added to any node in the chart.
   *
   * @param listener the listener to be added
   * @return a {@link Registration} for removing the listener
   */
  public Registration addChildrenAddedListener(
      ComponentEventListener<ChildrenAddedEvent> listener) {
    return addListener(ChildrenAddedEvent.class, listener);
  }

  /**
   * Fires a children added event.
   *
   * @param item the node that received new children
   * @param newChildren list of the newly added children
   * @param fromClient whether the event originated from the client side
   */
  protected void fireChildrenAddedEvent(OrgChartItem item, List<OrgChartItem> newChildren,
      boolean fromClient) {
    fireEvent(new ChildrenAddedEvent(this, item, newChildren, fromClient));
  }

  /**
   * Removes a specified node and all of its descendants from the chart.
   * <p>
   * This action updates both the server-side data model and the client-side visualization. If the
   * root node is removed, the chart will likely become empty. This operation is permanent for the
   * current state of the chart.
   *
   * @param nodeId the ID of the node to remove. All children and subsequent descendants of this
   *        node will also be removed from the chart.
   * @throws IllegalArgumentException if the {@code nodeId} is not found in the chart
   */
  public void removeNodes(Integer nodeId) {
    // Find the node set for removal
    OrgChartItem nodeToRemove = getById(nodeId, orgChartItem);
    if (nodeToRemove != null) {
      // Clear the removed node's children
      nodeToRemove.setChildren(Collections.emptyList());
      // Find parent and remove node from its children
      OrgChartItem parentNode = findParent(nodeId, orgChartItem);
      if (parentNode != null) {
        List<OrgChartItem> currentChildren = parentNode.getChildren();
        currentChildren.removeIf(child -> nodeId.equals(child.getId()));
        parentNode.setChildren(currentChildren);
      }

      // If removing the root, clear internal root reference
      if (this.orgChartItem != null && nodeId.equals(this.orgChartItem.getId())) {
          this.orgChartItem = null;
      }
      
      // Update the visual representation
      this.getElement().executeJs("this.removeNodes($0)", nodeId);
    } else {
      throw new IllegalArgumentException("Node not found: " + nodeId);
    }
  }

  @ClientCallable
  private void onNodesRemoved(String nodeId) {
    fireNodesRemovedEvent(Integer.valueOf(nodeId), true);
  }

  /**
   * Adds a listener for node removal events. The listener will be notified when a node and its
   * descendants are removed from the chart.
   *
   * @param listener the listener to be added
   * @return a {@link Registration} for removing the listener
   */
  public Registration addNodesRemovedListener(ComponentEventListener<NodesRemovedEvent> listener) {
    return addListener(NodesRemovedEvent.class, listener);
  }

  /**
   * Fires a nodes removed event.
   *
   * @param nodeId the ID of the removed node
   * @param fromClient whether the event originated from the client side
   */
  protected void fireNodesRemovedEvent(Integer nodeId, boolean fromClient) {
    fireEvent(new NodesRemovedEvent(this, nodeId, fromClient));
  }

  /**
   * Adds a new parent node to the organization chart. This method:
   * <ul>
   * <li>Updates the visual representation of the chart</li>
   * <li>Maintains the internal data structure by updating the root item</li>
   * </ul>
   * 
   * @param newParentItem the new root item of the chart
   */
  public void addParent(OrgChartItem newParentItem) {
    // Update the internal data structure
    if (this.orgChartItem != null) {
      // Set the old root as the only child of the new parent node
      newParentItem.setChildren(Collections.singletonList(this.orgChartItem));
    }
    // Update the chart's root to point to the new parent
    this.orgChartItem = newParentItem;

    // Update the visual representation by calling the client-side method addParent
    String parentJson = convertToJsonObj(newParentItem);
    this.getElement().executeJs("this.addParent($0)", parentJson);
  }

  /**
   * Handles parent addition events from the client side. The client sends the ID of the new
   * parent/root node.
   *
   * @param newParentId the ID of the newly added parent node
   */
  @ClientCallable
  private void onParentAdded(String newParentId) {
    OrgChartItem newParentItem = getById(Integer.valueOf(newParentId), orgChartItem);
    if (newParentItem != null) {
      fireParentAddedEvent(newParentItem, true);
    }
  }

  /**
   * Adds a listener for parent addition event. The listener will be notified when a new parent
   * (root) is added to the chart.
   *
   * @param listener the listener to be added
   * @return a {@link Registration} for removing the listener
   */
  public Registration addParentAddedListener(ComponentEventListener<ParentAddedEvent> listener) {
    return addListener(ParentAddedEvent.class, listener);
  }

  /**
   * Fires a parent added event.
   *
   * @param newParent the node that was added as the new parent/root
   * @param fromClient whether the event originated from the client side
   */
  protected void fireParentAddedEvent(OrgChartItem newParent, boolean fromClient) {
    fireEvent(new ParentAddedEvent(this, newParent, fromClient));
  }

  /**
   * Updates a node in the chart with new data.
   * <p>
   * This method updates the server-side data model and then calls the client-side function to
   * visually redraw the node with the new information.
   *
   * @param nodeId the ID of the node to update
   * @param newDataItem an {@link OrgChartItem} containing the new data to be merged. The ID of this
   *        item is ignored; only its other properties (name, title, custom data, etc) are used for
   *        the update.
   * @throws IllegalArgumentException if the {@code nodeId} is not found in the chart
   */
  public void updateNode(Integer nodeId, OrgChartItem newDataItem) {
    OrgChartItem nodeToUpdate = getById(nodeId, this.orgChartItem);
    if (nodeToUpdate != null) {
      // Update the server-side object
      if (newDataItem.getName() != null) {
        nodeToUpdate.setName(newDataItem.getName());
      }
      if (newDataItem.getTitle() != null) {
        nodeToUpdate.setTitle(newDataItem.getTitle());
      }
      if (newDataItem.getClassName() != null) {
        nodeToUpdate.setClassName(newDataItem.getClassName());
      }
      if (nodeToUpdate.isHybrid() != newDataItem.isHybrid()) {
        nodeToUpdate.setHybrid(newDataItem.isHybrid());
      }
      if (newDataItem.getData() != null) {
        newDataItem.getData().forEach(nodeToUpdate::setData);
      }

      // Call the client-side JS function to update the visual representation
      String newDataJson = convertToJsonObj(newDataItem);
      this.getElement().executeJs("this.updateNode($0, $1)", nodeId, newDataJson);
    } else {
      throw new IllegalArgumentException("Node not found: " + nodeId);
    }
  }

  @ClientCallable
  private void onNodeUpdated(String nodeId) {
    OrgChartItem updatedItem = getById(Integer.valueOf(nodeId), orgChartItem);
    if (updatedItem != null) {
      fireNodeUpdatedEvent(updatedItem, true);
    }
  }

  /**
   * Adds a listener for node updated event.
   *
   * @param listener the listener to be added
   * @return a {@link Registration} for removing the listener
   */
  public Registration addNodeUpdatedListener(ComponentEventListener<NodeUpdatedEvent> listener) {
    return addListener(NodeUpdatedEvent.class, listener);
  }

  /**
   * Fires a node updated event.
   *
   * @param item the updated node
   * @param fromClient whether the event originated from the client side
   */
  protected void fireNodeUpdatedEvent(OrgChartItem item, boolean fromClient) {
    fireEvent(new NodeUpdatedEvent(this, item, fromClient));
  }

}
