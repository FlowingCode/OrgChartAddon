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

import com.flowingcode.vaadin.addons.DemoLayout;
import com.flowingcode.vaadin.addons.demo.impl.TabbedDemoImpl;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@SuppressWarnings("serial")
@Route(value = "orgchart", layout = DemoLayout.class)
@StyleSheet("context://frontend/styles/orgchart/demo-styles.css")
@CssImport("./styles/orgchart/font-awesome.css")
public class OrgchartDemoView extends VerticalLayout {

	private static final String DRAGNDROP_DEMO = "Drag and Drop";
	private static final String BOTTOMTOP_DEMO = "Bottom to Top";
	private static final String DRAGNDROP_SOURCE = "https://github.com/FlowingCode/OrgChartAddon/blob/master/src/test/java/com/flowingcode/vaadin/addons/orgchart/DragAndDropExportDemo.java";
	private static final String BOTTOMTOP_SOURCE = "https://github.com/FlowingCode/OrgChartAddon/blob/master/src/test/java/com/flowingcode/vaadin/addons/orgchart/BottomTopDemo.java";

	public OrgchartDemoView() {
		TabbedDemoImpl<DragAndDropExportDemo> orgDemo = new TabbedDemoImpl<>(new DragAndDropExportDemo(),
				DRAGNDROP_DEMO, DRAGNDROP_SOURCE);
		orgDemo.addDemo(new BottomTopDemo(), BOTTOMTOP_DEMO, BOTTOMTOP_SOURCE);
		setSizeFull();
		add(orgDemo);
	}
}
