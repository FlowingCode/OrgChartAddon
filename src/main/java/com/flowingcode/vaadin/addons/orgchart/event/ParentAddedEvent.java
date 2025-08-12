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
import com.flowingcode.vaadin.addons.orgchart.OrgChartItem;
import com.vaadin.flow.component.ComponentEvent;

/**
 * Event fired when a new parent is added to the chart.
 */
@SuppressWarnings("serial")
public class ParentAddedEvent extends ComponentEvent<OrgChart> {
  
  private final OrgChartItem newParent;

  /**
   * Creates a new event.
   *
   * @param source     the component that fired the event
   * @param newParent  the item that was added as the new parent
   * @param fromClient true if the event originated from the client
   */
  public ParentAddedEvent(OrgChart source, OrgChartItem newParent, boolean fromClient) {
      super(source, fromClient);
      this.newParent = newParent;
  }

  /**
   * Gets the item that was added as the new parent/root.
   *
   * @return the new parent item
   */
  public OrgChartItem getNewParent() {
      return newParent;
  }
}