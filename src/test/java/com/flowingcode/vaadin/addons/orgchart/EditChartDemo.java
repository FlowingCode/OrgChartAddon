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

import com.flowingcode.vaadin.addons.demo.DemoSource;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Demo view to demonstrate how an {@code OrgChart} can be edited.
 * <p>
 * This view showcases the following operations:
 * <ul>
 * <li>Adding siblings to an existing node.</li>
 * <li>Adding children to an existing node.</li>
 * <li>Adding a new parent (root) to the entire chart.</li>
 * <li>Removing nodes from the chart.</li>
 * <li>Updating the data of a selected node.</li>
 * </ul>
 */
@SuppressWarnings("serial")
@PageTitle("Edit Chart")
@DemoSource
@Route(value = "orgchart/edit-chart", layout = OrgchartDemoView.class)
@CssImport("./styles/orgchart/edit-chart-demo-styles.css")
public class EditChartDemo extends VerticalLayout {

  private static final AtomicInteger idCounter = new AtomicInteger(100);

  private OrgChart orgChart;
  private OrgChartItem selectedNode;
  private TextField selectedNodeNameField;
  private VerticalLayout newNodeFieldsLayout;
  private HorizontalLayout newNodeButtonsLayout;
  private RadioButtonGroup<String> typeSelector;
  private Button addButton;
  private Button deleteButton;
  private Button updateButton;
  private Button resetButton;
  private HorizontalLayout newNodeActionsColumn;
  private RadioButtonGroup<String> actionSelector;

  public EditChartDemo() {
    setSizeFull();
    initChart();
    add(orgChart, getEditionLayout());
  }

  private void initChart() {
    orgChart = getChart();
    orgChart.addClassNames("chart-container", "editable-chart");
    orgChart.setChartTitle(
        "EDIT CHART - Add Children, Add Siblings, Add Parent, Remove Nodes, Edit Selected Node");
    orgChart.setChartNodeContent("title");

    // Add listener for node click
    orgChart.addOnNodeClickListener(e -> {
      selectedNode = e.getClickedItem();
      selectedNodeNameField.setValue(selectedNode.getName());
      updateComponentStates();
    });

    // Add listener to know when siblings are added
    orgChart.addSiblingsAddedListener(e -> {
      OrgChartItem targetNode = e.getItem();
      List<OrgChartItem> newSiblings = e.getNewSiblings();
      if (!newSiblings.isEmpty()) {
        String siblingNames =
            newSiblings.stream().map(OrgChartItem::getName).collect(Collectors.joining(", "));
        String message =
            String.format("New siblings \"%s\" added to %s", siblingNames, targetNode.getName());
        Notification.show(message);
        // Console - Show hierarchy updated after adding siblings
        System.out.println(
            "------ OrgChart updated: ------\n" + e.getSource().getOrgChartItem().toString());
      }
    });

    // Add listener to know when children are added
    orgChart.addChildrenAddedListener(e -> {
      OrgChartItem targetNode = e.getItem();
      List<OrgChartItem> newChildren = e.getNewChildren();
      String childrenNames =
          newChildren.stream().map(OrgChartItem::getName).collect(Collectors.joining(", "));
      String message =
          String.format("New children \"%s\" added to %s", childrenNames, targetNode.getName());
      Notification.show(message);
      // Console - Show hierarchy updated after adding children
      System.out.println(
          "------ OrgChart updated: ------\n" + e.getSource().getOrgChartItem().toString());
    });

    // Add listener on nodes removal
    orgChart.addNodesRemovedListener(e -> {
      String message = String.format("Item with id %s (and its descedant nodes) removed from chart",
          e.getNodeId());
      Notification.show(message);
      // Console - Show hierarchy updated after removing nodes
      System.out.println(
          "------ OrgChart updated: ------\n" + e.getSource().getOrgChartItem().toString());
    });

    // Add listener on new parent added
    orgChart.addParentAddedListener(e -> {
      String message =
          String.format("New parent \"%s\" added to chart", e.getNewParent().getName());
      Notification.show(message);
      // Console - Show hierarchy updated after adding a new parent
      System.out.println(
          "------ OrgChart updated: ------\n" + e.getSource().getOrgChartItem().toString());
    });

    // Add listener when a node data is updated
    orgChart.addNodeUpdatedListener(e -> {
      String message = String.format("Node \"%s\" was updated.", e.getUpdatedItem().getName());
      Notification.show(message);
      // Console - Show hierarchy updated after editing a node
      System.out.println(
          "------ OrgChart updated: ------\n" + e.getSource().getOrgChartItem().toString());
    });
  }

  private OrgChart getChart() {
    OrgChartItem item1 = new OrgChartItem(1, "John Williams", "Director");
    OrgChartItem item2 = new OrgChartItem(2, "Anna Thompson", "Administration");
    OrgChartItem item3 = new OrgChartItem(3, "Timothy Jones", "Sub-Director");
    item1.setChildren(Arrays.asList(item2, item3));
    OrgChartItem item4 = new OrgChartItem(4, "Louise Night", "Department 1");
    OrgChartItem item5 = new OrgChartItem(5, "John Porter", "Department 2");
    item2.setChildren(Arrays.asList(item4, item5));
    OrgChartItem item6 = new OrgChartItem(6, "Charles Thomas", "Department 3");
    item5.setChildren(Arrays.asList(item6));
    return new OrgChart(item1);
  }

  private VerticalLayout getEditionLayout() {
    // Main container for the entire panel
    VerticalLayout editionPanel = new VerticalLayout();
    editionPanel.addClassName("edition-panel");
    editionPanel.setSpacing(false);

    // Action Selector
    actionSelector = new RadioButtonGroup<>();
    actionSelector.setLabel("Select Action");
    actionSelector.setItems("Add", "Edit", "Delete");
    actionSelector.addValueChangeListener(event -> updateComponentStates());

    Div separator = new Div();
    separator.addClassName("edition-panel-separator");
    separator.setWidthFull();

    // Main Controls Layout
    HorizontalLayout mainControlsLayout = new HorizontalLayout();
    mainControlsLayout.setWidthFull();
    mainControlsLayout.addClassName("main-controls-layout");
    mainControlsLayout.setAlignItems(Alignment.BASELINE);

    // Selected node layout (selected node and relation type selector)
    VerticalLayout selectedNodeColumn = createVerticalLayout();
    selectedNodeColumn.addClassName("selected-node-layout");
    selectedNodeColumn.setAlignItems(Alignment.START);

    // Selected node name text field
    selectedNodeNameField = new TextField("Selected Node:");
    selectedNodeNameField.setPlaceholder("Node Name");
    selectedNodeNameField.setWidth("180px");
    selectedNodeNameField.setReadOnly(true);

    // Relation type selector
    typeSelector = new RadioButtonGroup<String>();
    typeSelector.setLabel("Select Relation Type:");
    typeSelector.setItems("Parent(root)", "Child", "Sibling");
    typeSelector.setValue("Child");
    typeSelector.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);

    // New node(s) layout (dynamic)
    newNodeFieldsLayout = createVerticalLayout();
    newNodeFieldsLayout.addClassName("new-nodes-layout");

    // Action buttons
    addButton = new Button("Add Child to Selected Node");
    deleteButton = new Button("Delete Selected Node");
    updateButton = new Button("Edit Selected Node");
    resetButton = new Button("Reset Chart");

    // Add value-change listener to relation type selector
    typeSelector.addValueChangeListener(event -> {
      switch (event.getValue()) {
        case "Parent(root)":
          addButton.setText("Add Parent Node");
          break;
        case "Sibling":
          addButton.setText("Add Sibling to Selected Node");
          break;
        default:
          addButton.setText("Add Child to Selected Node");
          break;
      }
      updateComponentStates();
    });

    selectedNodeColumn.add(selectedNodeNameField, typeSelector);

    // Add the first text field for a new node initially
    TextField initialNodeField = createNewNodeTextField();
    initialNodeField.setLabel("Add New Node Name:");
    newNodeFieldsLayout.add(initialNodeField);

    // Create add/remove buttons for new nodes
    // These buttons will be used to add or remove text fields for new nodes
    Button addButtonSmall = createIconButton(VaadinIcon.PLUS);
    Button removeButtonSmall = createIconButton(VaadinIcon.MINUS);

    newNodeButtonsLayout = new HorizontalLayout(addButtonSmall, removeButtonSmall);
    newNodeButtonsLayout.addClassName("new-nodes-buttons-layout");
    newNodeButtonsLayout.setPadding(false);
    newNodeButtonsLayout.setSpacing(false);

    // Click listener for the '+' button to add a new text field
    addButtonSmall.addClickListener(e -> newNodeFieldsLayout.add(createNewNodeTextField()));

    // Click listener for the '-' button to remove the last added text field
    removeButtonSmall.addClickListener(e -> {
      if (newNodeFieldsLayout.getComponentCount() > 1) {
        Component lastField =
            newNodeFieldsLayout.getComponentAt(newNodeFieldsLayout.getComponentCount() - 1);
        newNodeFieldsLayout.remove(lastField);
      }
    });

    // Adding new nodes layout
    newNodeActionsColumn = new HorizontalLayout(newNodeFieldsLayout, newNodeButtonsLayout);
    newNodeActionsColumn.setAlignItems(Alignment.BASELINE);

    // Add listeners to actions
    addButton.addClickListener(e -> onAddButtonClick());
    deleteButton.addClickListener(e -> onDeleteButtonClick());
    updateButton.addClickListener(e -> onUpdateButtonClick());
    resetButton.addClickListener(e -> onResetButtonClick());

    // Layout for action buttons
    VerticalLayout actionButtonsColumn = createVerticalLayout();
    actionButtonsColumn.add(addButton, deleteButton, updateButton, resetButton);
    actionButtonsColumn.setJustifyContentMode(JustifyContentMode.END);
    actionButtonsColumn.setAlignItems(Alignment.END);

    // Add all columns to the main controls layout
    mainControlsLayout.add(selectedNodeColumn, newNodeActionsColumn, actionButtonsColumn);

    // Add the actionSelector and the main controls to the final panel
    editionPanel.add(actionSelector, separator, mainControlsLayout);

    // Set the initial state
    updateComponentStates();

    return editionPanel;
  }

  private void updateComponentStates() {
    String action = actionSelector.getValue();
    boolean isAdd = "Add".equals(action);
    boolean isEdit = "Edit".equals(action);
    boolean isDelete = "Delete".equals(action);
    boolean nodeIsSelected = (selectedNode != null);

    String relation = typeSelector.getValue();
    boolean isParentMode = "Parent(root)".equals(relation);
    
    if(isParentMode) {
      resetNewNodeFields();
    }
    
    typeSelector.setVisible(isAdd);
    newNodeActionsColumn.setVisible(isAdd);
    newNodeButtonsLayout.setVisible(isAdd && !isParentMode);
    selectedNodeNameField.setVisible(isEdit || isDelete || (isAdd && !isParentMode));

    addButton.setEnabled(isAdd);
    updateButton.setEnabled(isEdit && nodeIsSelected);
    deleteButton.setEnabled(isDelete && nodeIsSelected);
  }

  private void onAddButtonClick() {
    // Make sure a node is selected
    if (selectedNode == null && selectedNodeNameField.isVisible()) {
      Notification.show("Please select a node first.");
      return;
    }

    // Get all non-empty names from the text fields
    List<String> newNodeNames =
        newNodeFieldsLayout.getChildren().filter(component -> component instanceof TextField)
            .map(component -> ((TextField) component).getValue())
            .filter(name -> name != null && !name.trim().isEmpty()).collect(Collectors.toList());

    if (newNodeNames.isEmpty()) {
      Notification.show("Please enter a name for the new node(s).");
      return;
    }

    // Create new OrgChartItem objects with unique IDs
    List<OrgChartItem> newItems = new ArrayList<>();
    for (String name : newNodeNames) {
      int newId = idCounter.getAndIncrement();
      OrgChartItem newItem = new OrgChartItem(newId, name, "Undefined");
      newItems.add(newItem);
    }

    // Add the new nodes to the chart
    String relationType = typeSelector.getValue();

    switch (relationType) {
      case "Child":
        orgChart.addChildren(selectedNode.getId(), newItems);
        break;
      case "Sibling":
        try {
          orgChart.addSiblings(selectedNode.getId(), newItems);
        } catch (IllegalArgumentException ex) {
          Notification.show(ex.getMessage());
        }
        break;
      case "Parent(root)":
        orgChart.addParent(newItems.get(0));
        break;
    }

    // Reset the text fields for the next operation
    resetNewNodeFields();
    // Clear the text from the first field
    ((TextField) newNodeFieldsLayout.getComponentAt(0)).clear();
  }

  private void onDeleteButtonClick() {
    if (selectedNode == null) {
      Notification.show("Please select a node to delete.");
      return;
    }

    Integer selectedNodeId = selectedNode.getId();
    // Check if the selected node is the root
    boolean isRootNode = selectedNodeId.equals(orgChart.getOrgChartItem().getId());

    if (isRootNode) {
      // If it is the root, create and show a confirmation dialog
      ConfirmDialog dialog = new ConfirmDialog();
      dialog.setHeader("Delete Entire Chart?");
      dialog.setText(
          "Are you sure you want to delete the root node? This action will remove the entire chart.");

      dialog.setConfirmText("Delete Chart");
      dialog.setConfirmButtonTheme("error primary");
      dialog.setCancelable(true);

      dialog.addConfirmListener(event -> {
        orgChart.removeNodes(selectedNodeId);
        clearSelectedNode();
        Notification.show("Chart deleted.");
      });

      dialog.open();
    } else {
      orgChart.removeNodes(selectedNodeId);
      clearSelectedNode();
      Notification.show("Node deleted.");
    }
  }

  private void onUpdateButtonClick() {
    if (selectedNode == null) {
      Notification.show("Please select a node to update.");
      return;
    }

    // Create a dialog for editing
    ConfirmDialog dialog = new ConfirmDialog();
    dialog.setHeader("Edit Node Details");

    // Create fields for name and title, pre-filled with current data
    TextField nameField = new TextField("Name");
    nameField.setValue(selectedNode.getName());
    nameField.setWidthFull();

    TextField titleField = new TextField("Title");
    titleField.setValue(selectedNode.getTitle());
    titleField.setWidthFull();

    dialog.add(new VerticalLayout(nameField, titleField));
    dialog.setConfirmText("Save");
    dialog.setConfirmButtonTheme("primary");
    dialog.setCancelable(true);

    // Add a listener for the confirm (Save) button
    dialog.addConfirmListener(event -> {
      // Create a new item with the updated data (using a temporary ID)
      OrgChartItem updatedData = new OrgChartItem(0, nameField.getValue(), titleField.getValue());

      // Call the updateNode method
      orgChart.updateNode(selectedNode.getId(), updatedData);
    });

    dialog.open();
  }

  private void onResetButtonClick() {
    OrgChart oldChart = this.orgChart;
    initChart();
    this.replace(oldChart, this.orgChart);
    clearSelectedNode();
    actionSelector.setValue(null);
  }

  private void clearSelectedNode() {
    selectedNode = null;
    selectedNodeNameField.clear();
    updateComponentStates();
  }

  private Button createIconButton(VaadinIcon icon) {
    Button iconButton = new Button(icon.create());
    iconButton.addThemeVariants(ButtonVariant.LUMO_ICON, ButtonVariant.LUMO_SMALL,
        ButtonVariant.LUMO_TERTIARY_INLINE);
    return iconButton;
  }

  private TextField createNewNodeTextField() {
    TextField newNodeTextField = new TextField();
    newNodeTextField.setPlaceholder("Name");
    newNodeTextField.setWidth("150px");
    return newNodeTextField;
  }

  private VerticalLayout createVerticalLayout() {
    VerticalLayout verticalLayout = new VerticalLayout();
    verticalLayout.setWidth("auto");
    verticalLayout.setSpacing(false);
    verticalLayout.setPadding(false);
    return verticalLayout;
  }

  private void resetNewNodeFields() {
    // Reset the text fields for the next operation
    while (newNodeFieldsLayout.getComponentCount() > 1) {
      // Remove all fields except the first one
      newNodeFieldsLayout.remove(newNodeFieldsLayout.getComponentAt(1));
    }
  }
}
