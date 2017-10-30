/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.flowingcode.vaadin.addons.orgchart.demo;

import com.flowingcode.vaadin.addons.orgchart.OrgChart;
import com.flowingcode.vaadin.addons.orgchart.OrgChartItem;
import com.vaadin.flow.router.View;
import com.vaadin.router.Title;
import com.vaadin.ui.Composite;
import com.vaadin.ui.html.Div;

/**
 * Hello World view based on components. This is a composite based on a div
 * component.
 */
@SuppressWarnings("serial")
@Title("Example of Organizational Chart")
public class OrgChartView extends Composite<Div> implements View {
    /**
     * Creates the hello world Components API based component.
     */
    public OrgChartView() {
        
    	OrgChartItem parentItem = new OrgChartItem(1, "John Smith", "Director");
    	OrgChartItem childItem = new OrgChartItem(2, "Anna Anderson", "Development Manager");
    	parentItem.addChild(childItem);
    	OrgChartItem childItem2  = new OrgChartItem(3, "Morgan Spacey", "Infrastructure Manager");
    	parentItem.addChild(childItem2);
    	childItem  = new OrgChartItem(4, "Kevin Freeman", "SysAdmin");
    	childItem2.addChild(childItem);
    	
        OrgChart oc = new OrgChart(parentItem);
        
        /*
         * Add the input and the greeting to the Div component that makes up the
         * content of this Composite subclass
         */
        getContent().add(oc);
    }

}
