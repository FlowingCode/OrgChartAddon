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

import com.flowingcode.vaadin.addons.orgchart.OrgChart;
import com.vaadin.flow.component.ComponentEvent;

/**
 * Event fired when a node and its descendants are removed from the organization chart. Contains
 * information about the removed node.
 */
@SuppressWarnings("serial")
public class NodesRemovedEvent extends ComponentEvent<OrgChart> {
  private final Integer nodeId;

  /**
   * Creates a new nodes removed event.
   *
   * @param source the chart component that fired the event
   * @param nodeId the ID of the removed node
   * @param fromClient whether the event originated from the client side
   */
  public NodesRemovedEvent(OrgChart source, Integer nodeId, boolean fromClient) {
    super(source, fromClient);
    this.nodeId = nodeId;
  }

  /**
   * Gets the ID of the removed node.
   *
   * @return the node ID
   */
  public Integer getNodeId() {
    return nodeId;
  }

}
