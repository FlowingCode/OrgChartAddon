package com.flowingcode.vaadin.addons.orgchart.demo;

/*-
 * #%L
 * OrgChart Add-on Demo
 * %%
 * Copyright (C) 2017 - 2018 FlowingCode S.A.
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

import java.util.Arrays;

import javax.servlet.annotation.WebServlet;

import com.flowingcode.vaadin.addons.orgchart.OrgChart;
import com.flowingcode.vaadin.addons.orgchart.OrgChartItem;
import com.flowingcode.vaadin.addons.orgchart.client.enums.ChartDirectionEnum;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalSplitPanel;

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
    	// org chart 1
        OrgChart component1 = getExample1();
        component1.setNodeTemplate("<div class='title'>${item.title}</div><div class='middle content'>${item.name}</div><div class='custom content'>${item.data?item.data.mail:''}</div>");
        component1.setChartTitle("My Organization Chart Demo - Example 1 - CHART EXPORT AS PICTURE AND DRAG & DROP");    
        component1.setChartNodeContent("title");
        component1.setChartExportButton(true);
        component1.setChartExpandCollapse(true);        
        component1.setChartDraggable(true);
        
        component1.addDragAndDropListener(e -> System.out.println("------ OrgChart updated - Item dragged: " + e.getDraggedItem().getName() + "------\n" +  e.getOrgChart().getOrgChartItem().toString()));
    
        OrgChart component2 = getExample2();
        component2.setChartTitle("My Organization Chart Demo - Example 2 - BOTTOM TO TOP DIRECTION");    
        component2.setChartNodeContent("title");
        component2.setChartDirection(ChartDirectionEnum.BOTTOM_TO_TOP.getAbreviation());
               
        VerticalSplitPanel layout = new VerticalSplitPanel(component1, component2);
        setContent(layout);
    }    
        
    public OrgChart getExample1() {
    	OrgChartItem item1 = new OrgChartItem(1, "John Williams", "Director");
    	item1.setData("mail", "jwilliams@example.com");
    	item1.setClassName("blue-node");
        OrgChartItem item2 = new OrgChartItem(2, "Anna Thompson", "Administration");
        item2.setData("mail", "athomp@example.com");
        item2.setClassName("blue-node");
        OrgChartItem item3 = new OrgChartItem(3, "Timothy Jones", "Sub-Director");
        item3.setData("mail", "tjonees@example.com");
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
