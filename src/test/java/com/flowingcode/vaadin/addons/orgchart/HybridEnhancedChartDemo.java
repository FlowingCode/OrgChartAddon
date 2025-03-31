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

package com.flowingcode.vaadin.addons.orgchart;

import java.util.Arrays;
import com.flowingcode.vaadin.addons.demo.DemoSource;
import com.flowingcode.vaadin.addons.orgchart.extra.TemplateLiteralRewriter;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * Chart demo showing how to display a hybrid demo (horizontal + vertical) and how to customize the
 * nodes to show information as cards.
 * 
 */
@SuppressWarnings("serial")
@PageTitle("Hybrid")
@DemoSource
@Route(value = "orgchart/hybrid", layout = OrgchartDemoView.class)
@CssImport("./styles/orgchart/hybrid-demo-styles.css")
public class HybridEnhancedChartDemo extends VerticalLayout {

  public HybridEnhancedChartDemo() {    
    OrgChart orgchart = getExample1();
    String nodeTemplate = "<div class='title'>"
        + "${item.data.imageUrl?`<img class='avatar'src=${item.data.imageUrl}></img>`:''}"
        + "<div class='item-name'>${item.name}</div>"
        + "<div class='item-title'>${item.title}</div>"
        + "${item.data.phone?`<div class='item-phone'><i class='fa fa-phone'></i>${item.data.phone}</div>`:''}"
        + "${item.data.mail?`<div class='item-email'><i class='fa fa-envelope-o'></i>${item.data.mail}</div>`:''}"
        + "</div>";
    orgchart.setNodeTemplate("item", TemplateLiteralRewriter.rewriteFunction(nodeTemplate));
    orgchart.addClassNames("chart-container", "hybrid-chart");
    orgchart.setChartNodeContent("title");

    // make the chart to show children as vertical starting in level 3
    orgchart.setChartVerticalDepth(3);

    // images license
    Div iconsDiv = new Div();
    Anchor iconsAnchor = new Anchor("https://www.flaticon.com/free-icons/people", "People icons created by Creartive - Flaticon");
    iconsAnchor.setTitle("people icons");
    iconsDiv.add(iconsAnchor);
    
    orgchart.setChartTitle("My Organization Chart Demo - Example 4 - HYBRID CHART WITH CUSTOM TEMPLATE" + iconsDiv.getElement());

    setSizeFull();
    add(orgchart);
    
    
  }

  private OrgChart getExample1() {
    OrgChartItem item1 = new OrgChartItem(1, "John Williams", "Director");
    item1.setData("mail", "jwilliams@example.com");
    item1.setData("imageUrl", "images/1.png");
    item1.setData("phone", "(333) 445-5898");
    OrgChartItem item2 = new OrgChartItem(2, "Anna Thompson", "Administration");
    item2.setData("mail", "athomp@example.com");
    item2.setData("imageUrl", "images/2.png");
    item2.setData("phone", "(333) 407-5559");
    OrgChartItem item3 = new OrgChartItem(3, "Timothy Henry Jones", "Administration");
    item3.setData("mail", "timothy.jones@example.com");
    item3.setData("imageUrl", "images/3.png");
    item3.setData("phone", "(344) 508-7894");
    OrgChartItem item4 = new OrgChartItem(4, "Louise Night", "Administration");
    item4.setData("mail", "lnight@example.com");
    item4.setData("imageUrl", "images/4.png");
   
    OrgChartItem item5 = new OrgChartItem(5, "John Porter", "Head of Department");
    item5.setData("mail", "jporter@example.com");
    item5.setData("imageUrl", "images/5.png");    
    OrgChartItem item6 = new OrgChartItem(6, "Charles Thomas", "Head of Department");
    item6.setData("mail", "ctomas@example.com");
    item6.setData("imageUrl", "images/6.png");    
    OrgChartItem item7 = new OrgChartItem(7, "Angela Wood", "Section 1");
    item7.setData("mail", "awood@example.com");
    item7.setData("imageUrl", "images/7.png");
    OrgChartItem item8 = new OrgChartItem(8, "Martha Brown", "Section 1");
    item8.setData("imageUrl", "images/8.png");
    OrgChartItem item9 = new OrgChartItem(9, "Mary Parker", "Section 1.1");
    item9.setData("imageUrl", "images/9.png");
    OrgChartItem item10 = new OrgChartItem(10, "Mary Williamson", "Section 2");
    item10.setData("imageUrl", "images/10.png");
    item10.setData("phone", "(234) 567-9323");
    
    item1.setChildren(Arrays.asList(item2, item3, item4));
    item4.addChildren(item5);
    item2.setChildren(Arrays.asList(item6));
    item6.setChildren(Arrays.asList(item7, item8));
    item8.setChildren(Arrays.asList(item9));    
    item5.setChildren(Arrays.asList(item10));
    return new OrgChart(item1);
  }

}
