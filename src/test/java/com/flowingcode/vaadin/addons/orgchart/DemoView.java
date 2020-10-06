package com.flowingcode.vaadin.addons.orgchart;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@SuppressWarnings("serial")
@Route("")
public class DemoView extends VerticalLayout implements BeforeEnterObserver {

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		event.forwardTo(OrgchartDemoView.class);
	}

}
