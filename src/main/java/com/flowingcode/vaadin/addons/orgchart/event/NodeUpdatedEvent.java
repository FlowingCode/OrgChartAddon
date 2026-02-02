/*-
 * #%L
 * OrgChart Add-on
 * %%
 * Copyright (C) 2017 - 2026 Flowing Code S.A.
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
