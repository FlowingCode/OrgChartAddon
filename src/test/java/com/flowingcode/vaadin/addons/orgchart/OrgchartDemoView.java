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
import com.flowingcode.vaadin.addons.GithubLink;
import com.flowingcode.vaadin.addons.demo.TabbedDemo;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.Route;

@SuppressWarnings("serial")
@ParentLayout(DemoLayout.class)
@Route("orgchart")
@StyleSheet("context://frontend/styles/orgchart/demo-styles.css")
@StyleSheet("context://frontend/styles/orgchart/font-awesome.css")
@GithubLink("https://github.com/FlowingCode/OrgChartAddon")
public class OrgchartDemoView extends TabbedDemo {

  public OrgchartDemoView() {
	addDemo(DragAndDropExportDemo.class);
	addDemo(BottomTopDemo.class);
	addDemo(ImageInTitleDemo.class);
  }
}
