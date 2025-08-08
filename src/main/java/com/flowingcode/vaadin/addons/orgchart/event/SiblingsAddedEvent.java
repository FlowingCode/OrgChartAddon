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
package com.flowingcode.vaadin.addons.orgchart.event;

import java.util.ArrayList;
import java.util.List;

import com.flowingcode.vaadin.addons.orgchart.OrgChart;
import com.flowingcode.vaadin.addons.orgchart.OrgChartItem;
import com.vaadin.flow.component.ComponentEvent;

/**
 * Event fired when siblings are added to a node in the organization chart. Contains information
 * about both the target node and the newly added siblings.
 */
@SuppressWarnings("serial")
public class SiblingsAddedEvent extends ComponentEvent<OrgChart> {
  private final OrgChartItem item;
  private final List<OrgChartItem> newSiblings;

  /**
   * Creates a new siblings added event.
   *
   * @param source the chart component that fired the event
   * @param item the node that received new siblings
   * @param newSiblings list of the newly added siblings
   * @param fromClient whether the event originated from the client side
   */
  public SiblingsAddedEvent(OrgChart source, OrgChartItem item, List<OrgChartItem> newSiblings,
      boolean fromClient) {
    super(source, fromClient);
    this.item = item;
    this.newSiblings = new ArrayList<>(newSiblings);
  }

  /**
   * Gets the node that received new siblings.
   *
   * @return the node
   */
  public OrgChartItem getItem() {
    return item;
  }

  /**
   * Gets the list of the newly added siblings. 
   *
   * @return the list of new sibling
   */
  public List<OrgChartItem> getNewSiblings() {
    return newSiblings;
  }
}
