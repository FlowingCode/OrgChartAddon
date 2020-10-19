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
package com.flowingcode.vaadin.addons.orgchart;

import com.flowingcode.vaadin.addons.orgchart.extra.TemplateLiteralRewriter;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import java.util.Arrays;

@SuppressWarnings("serial")
public class DragAndDropExportDemo extends VerticalLayout {

	public DragAndDropExportDemo() {
		OrgChart component1 = getExample1();
		String nodeTemplate = "<div class='title'>${item.title}</div>"
				+ "<div class='middle content'>${item.name}</div>"
				+ "${item.data.mail?`<div class='custom content'>${item.data.mail}</div>`:''}";
		component1.setNodeTemplate("item", TemplateLiteralRewriter.rewriteFunction(nodeTemplate));
		component1.addClassName("chart-container");

		component1.setChartTitle(
				"My Organization Chart Demo - Example 1 - CHART EXPORT AS PICTURE AND DRAG & DROP, CUSTOM TEMPLATE");
		component1.setChartNodeContent("title");
		component1.setChartExportButton(true);
		component1.setChartExpandCollapse(true);
		component1.setChartDraggable(true);
		component1.initializeChart();
		component1.addDragAndDropListener(e -> System.out.println("------ OrgChart updated - Item dragged: "
				+ e.getDraggedItem().getName() + "------\n" + e.getOrgChart().getOrgChartItem().toString()));

		component1.addOnNodeClickListener(e -> Notification.show("Item clicked: " + e.getClickedItem().getName()));

		setSizeFull();
		add(component1);
	}

	private OrgChart getExample1() {
		OrgChartItem item1 = new OrgChartItem(1, "John Williams", "Director");
		item1.setData("mail", "jwilliams@example.com");
		item1.setClassName("blue-node");
		OrgChartItem item2 = new OrgChartItem(2, "Anna Thompson", "Administration");
		item2.setData("mail", "athomp@example.com");
		item2.setClassName("blue-node");
		OrgChartItem item3 = new OrgChartItem(3, "Timothy Albert Henry Jones ",
				"Sub-Director of Administration Department");
		item3.setData("mail", "timothy.albert.jones@example.com");
		item1.setChildren(Arrays.asList(item2, item3));
		OrgChartItem item4 = new OrgChartItem(4, "Louise Night", "Department 1");
		item4.setData("mail", "lnight@example.com");
		OrgChartItem item5 = new OrgChartItem(5, "John Porter", "Department 2");
		item5.setData("mail", "jporter@example.com");
		OrgChartItem item6 = new OrgChartItem(6, "Charles Thomas", "Department 3");
		item6.setData("mail", "ctomas@example.com");
		item2.setChildren(Arrays.asList(item4, item5, item6));
		OrgChartItem item7 = new OrgChartItem(7, "Michael Wood", "Section 3.1");
		OrgChartItem item8 = new OrgChartItem(8, "Martha Brown", "Section 3.2");
		OrgChartItem item9 = new OrgChartItem(9, "Mary Parker", "Section 3.3");
		OrgChartItem item10 = new OrgChartItem(10, "Mary Williamson", "Section 3.4");
		item6.setChildren(Arrays.asList(item7, item8, item9, item10));
		return new OrgChart(item1);
	}
}
