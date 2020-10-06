package com.flowingcode.vaadin.addons.orgchart;

import com.flowingcode.vaadin.addons.orgchart.client.enums.ChartDirectionEnum;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import java.util.Arrays;

@SuppressWarnings("serial")
public class BottomTopDemo extends VerticalLayout {

	public BottomTopDemo() {
		OrgChart component2 = getExample2();
		component2.addClassName("chart-container");
		component2.setChartTitle("My Organization Chart Demo - Example 2 - BOTTOM TO TOP DIRECTION");
		component2.setChartNodeContent("title");
		component2.setChartDirection(ChartDirectionEnum.BOTTOM_TO_TOP.getAbreviation());
		component2.initializeChart();

		setSizeFull();
		add(component2);
	}

	private OrgChart getExample2() {
		OrgChartItem item1 = new OrgChartItem(1, "John Williams", "Director");
		OrgChartItem item2 = new OrgChartItem(2, "Anna Thompson", "Administration");
		OrgChartItem item3 = new OrgChartItem(3, "Timothy Jones", "Sub-Director");
		item1.setChildren(Arrays.asList(item2, item3));
		OrgChartItem item4 = new OrgChartItem(4, "Louise Night, Louise Night, Louise Night", "Department 1");
		OrgChartItem item5 = new OrgChartItem(5, "John Porter",
				"Department 2, Department 2, Department 2, Department 2, Department 2");
		item2.setChildren(Arrays.asList(item4, item5));
		OrgChartItem item6 = new OrgChartItem(6, "Charles Thomas", "Department 3");
		item5.setChildren(Arrays.asList(item6));
		return new OrgChart(item1);
	}
}
