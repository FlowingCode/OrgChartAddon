package com.flowingcode.vaadin.orgchart.demo;

import java.util.Arrays;

import javax.servlet.annotation.WebServlet;

import com.flowingcode.vaadin.orgchart.OrgChart;
import com.flowingcode.vaadin.orgchart.OrgChartLevelItem;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("demo")
@Title("OrgChart Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        OrgChartLevelItem item1 = new OrgChartLevelItem(1, "John Williams", "Director");
        OrgChartLevelItem item2 = new OrgChartLevelItem(2, "Anna Thompson", "Administration");
        OrgChartLevelItem item3 = new OrgChartLevelItem(3, "Timothy Jones", "Sub-Director");
        OrgChartLevelItem item4 = new OrgChartLevelItem(4, "Louise Night", "Department 1");
        OrgChartLevelItem item5 = new OrgChartLevelItem(5, "John Porter", "Department 2");
        OrgChartLevelItem item6 = new OrgChartLevelItem(6, "Charles Thomas", "Department 3");
        OrgChartLevelItem item7 = new OrgChartLevelItem(7, "Michael Wood", "Section 3.1");
        OrgChartLevelItem item8 = new OrgChartLevelItem(8, "Martha Brown", "Section 3.2");
        
        item1.setChildren(Arrays.asList(item2, item3));
        item2.setChildren(Arrays.asList(item4, item5, item6));
        item6.setChildren(Arrays.asList(item7, item8));
      
        OrgChart component = new OrgChart(item1);        
        component.setChartTitle("My Organization Chart Demo");    
        component.setChartZoom(true);
//        component.setChartExportButton(true);
//        component.setChartExportFileExtension(ChartConstants.CHART_EXPORT_EXTENSION_PDF);
//        component.setChartDirection(ChartDirectionEnum.BOTTOM_TO_TOP.getAbreviation());
       
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.addComponent(component);
//        layout.setComponentAlignment(component, Alignment.TOP_CENTER);
        setContent(layout);
    }    
    
}
