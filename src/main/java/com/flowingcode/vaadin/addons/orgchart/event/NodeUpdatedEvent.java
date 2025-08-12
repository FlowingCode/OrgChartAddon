package com.flowingcode.vaadin.addons.orgchart.event;

import com.flowingcode.vaadin.addons.orgchart.OrgChart;
import com.flowingcode.vaadin.addons.orgchart.OrgChartItem;
import com.vaadin.flow.component.ComponentEvent;


/**
 * Event thrown when a node is updated.
 */
@SuppressWarnings("serial")
public class NodeUpdatedEvent extends ComponentEvent<OrgChart> {
  private final OrgChartItem updatedItem;

  /**
   * Creates a node updated event.
   *
   * @param source the chart component that fired the event
   * @param updatedItem the node being updated
   * @param fromClient whether the event originated from the client side
   */
  public NodeUpdatedEvent(OrgChart source, OrgChartItem updatedItem, boolean fromClient) {
    super(source, fromClient);
    this.updatedItem = updatedItem;
  }

  /**
   * Gets the updated node.
   *
   * @return the updated node item
   */
  public OrgChartItem getUpdatedItem() {
    return updatedItem;
  }

}
