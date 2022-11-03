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

import java.util.Arrays;

import com.flowingcode.vaadin.addons.demo.DemoSource;
import com.flowingcode.vaadin.addons.orgchart.extra.TemplateLiteralRewriter;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@SuppressWarnings("serial")
@PageTitle("Image in Title")
@DemoSource
@Route(value = "orgchart/image-title", layout = OrgchartDemoView.class)
public class ImageInTitleDemo extends VerticalLayout {

  public ImageInTitleDemo() {
    OrgChart component = getOrgChartData();
    String nodeTemplate =
        "<div class='title'>"
    		+ "${item.data.imageUrl?`<img src=${item.data.imageUrl}></img>`:''}"
        	+ "${item.title}</div>"	
            + "<div class='middle content'>${item.name}</div>"
            + "${item.data.mail?`<div class='custom content'>${item.data.mail}</div>`:''}";
    component.setNodeTemplate("item", TemplateLiteralRewriter.rewriteFunction(nodeTemplate));
    component.addClassNames("chart-container", "image-title-demo");

    component.setChartTitle(
        "My Organization Chart Demo - Example 3 - CUSTOM TEMPLATE WITH IMAGE IN TITLE");
    component.setChartNodeContent("title");
    component.setChartExportButton(true);
    component.setChartExpandCollapse(true);
    
    setSizeFull();
    add(component);
  }

  private OrgChart getOrgChartData() {
    OrgChartItem item1 = new OrgChartItem(1, "John Williams", "Director");
    item1.setData("mail", "jwilliams@example.com");
    item1.setData("imageUrl", "images/users.png");
    
    OrgChartItem item2 = new OrgChartItem(2, "Anna Thompson", "Administration");
    item2.setData("mail", "athomp@example.com");
    item2.setData("imageUrl", "images/users.png");
    
    OrgChartItem item3 =
        new OrgChartItem(
            3, "Timothy Albert Henry Jones ", "Sub-Director of Administration Department");
    item3.setData("mail", "timothy.albert.jones@example.com");
    item3.setData("imageUrl", "images/user.png");
    
    item1.setChildren(Arrays.asList(item2, item3));
    
    OrgChartItem item4 = new OrgChartItem(4, "Louise Night", "Department 1");
    item4.setData("mail", "lnight@example.com");
    item4.setData("imageUrl", "images/user.png");
    
    OrgChartItem item5 = new OrgChartItem(5, "John Porter", "Department 2");
    item5.setData("mail", "jporter@example.com");
    item5.setData("imageUrl", "images/user.png");
    
    OrgChartItem item6 = new OrgChartItem(6, "Charles Thomas", "Department 3");
    item6.setData("mail", "ctomas@example.com");
    item6.setData("imageUrl", "images/users.png");
    
    item2.setChildren(Arrays.asList(item4, item5, item6));
    
    OrgChartItem item7 = new OrgChartItem(7, "Michael Wood", "Section 3.1");
    item7.setData("imageUrl", "images/user.png");
    
    OrgChartItem item8 = new OrgChartItem(8, "Martha Brown", "Section 3.2");
    item8.setData("imageUrl", "images/user.png");
    
    OrgChartItem item9 = new OrgChartItem(9, "Mary Parker", "Section 3.3");
    item9.setData("imageUrl", "images/user.png");
    OrgChartItem item10 = new OrgChartItem(10, "Mary Williamson", "Section 3.4");
    item10.setData("imageUrl", "images/user.png");
    
    item6.setChildren(Arrays.asList(item7, item8, item9, item10));
    
    return new OrgChart(item1);
  }
}
