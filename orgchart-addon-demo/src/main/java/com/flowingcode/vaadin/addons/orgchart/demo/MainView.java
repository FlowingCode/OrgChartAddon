package com.flowingcode.vaadin.addons.orgchart.demo;

import java.util.Arrays;

import javax.servlet.annotation.WebServlet;

import com.flowingcode.vaadin.addons.orgchart.ChartDirectionEnum;
import com.flowingcode.vaadin.addons.orgchart.OrgChart;
import com.flowingcode.vaadin.addons.orgchart.OrgChartItem;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout.Orientation;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinServlet;
import com.vaadin.flow.server.VaadinServletConfiguration;

/**
 * The main view contains a menu and a container for either of the hello world
 * variants.
 */
@SuppressWarnings("serial")
@Route(value = "")
public class MainView extends VerticalLayout {

	@WebServlet(urlPatterns = "/*", name = "OrgChartDemoServlet")
	@VaadinServletConfiguration(productionMode = false)
	public static class Servlet extends VaadinServlet {
	}
	
	public MainView() {
		OrgChart component1 = getExample1();
		component1.setChartTitle("My Organization Chart Demo - Example 1 - CHART EXPORT AS PICTURE");
		component1.setChartNodeContent("title");
		component1.setChartExportButton(true);
		component1.setChartExpandCollapse(true);
		
		OrgChart component2 = getExample2();
		component2.setChartTitle("My Organization Chart Demo - Example 2 - BOTTOM TO TOP DIRECTION");
		component2.setChartNodeContent("title");
		component2.setChartDirection(ChartDirectionEnum.BOTTOM_TO_TOP.getAbreviation());

		SplitLayout layout = new SplitLayout(component1,component2);
		layout.setOrientation(Orientation.VERTICAL);
		layout.setSizeFull();
		this.setSizeFull();
		
		add(layout);
		
	}

	public OrgChart getExample1() {
		OrgChartItem item1 = new OrgChartItem(1, "John Williams", "Director");
		OrgChartItem item2 = new OrgChartItem(2, "Anna Thompson", "Administration");
		OrgChartItem item3 = new OrgChartItem(3, "Timothy Jones", "Sub-Director");
		item1.setChildren(Arrays.asList(item2, item3));
		OrgChartItem item4 = new OrgChartItem(4, "Louise Night", "Department 1");
		OrgChartItem item5 = new OrgChartItem(5, "John Porter", "Department 2");
		OrgChartItem item6 = new OrgChartItem(6, "Charles Thomas", "Department 3");
		item2.setChildren(Arrays.asList(item4, item5, item6));
		OrgChartItem item7 = new OrgChartItem(7, "Michael Wood", "Section 3.1");
		OrgChartItem item8 = new OrgChartItem(8, "Martha Brown", "Section 3.2");
		OrgChartItem item9 = new OrgChartItem(9, "Mary Parker", "Section 3.3");
		OrgChartItem item10 = new OrgChartItem(10, "Mary Williamson", "Section 3.4");
		item6.setChildren(Arrays.asList(item7, item8, item9, item10));
		return new OrgChart(item1);
	}

	public OrgChart getExample2() {
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

}
