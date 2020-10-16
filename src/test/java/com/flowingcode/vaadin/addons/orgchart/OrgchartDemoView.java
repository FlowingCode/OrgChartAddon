package com.flowingcode.vaadin.addons.orgchart;

import com.flowingcode.vaadin.addons.DemoLayout;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout.Orientation;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
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
		SplitLayout layout = new SplitLayout();
		layout.setOrientation(Orientation.HORIZONTAL);
		layout.addToPrimary(new DragAndDropExportDemo());
		layout.setSizeFull();
		IFrame iframe = new IFrame();
		iframe.getElement().setAttribute("frameborder", "0");
		iframe.getElement().setAttribute("srcdoc", getSrcdoc(DRAGNDROP_SOURCE));
		iframe.setSizeFull();
		layout.addToSecondary(iframe);

		Tabs tabs = new Tabs();
		Tab demo1 = new Tab(DRAGNDROP_DEMO);
		Tab demo2 = new Tab(BOTTOMTOP_DEMO);

		tabs.setWidthFull();
		tabs.add(demo1, demo2);
		tabs.setSelectedTab(demo1);

		Checkbox orientationCB = new Checkbox("Toggle Orientation");
		orientationCB.setValue(true);
		orientationCB.addClassName("smallcheckbox");
		orientationCB.addValueChangeListener(cb -> {
			if (cb.getValue()) {
				layout.setOrientation(Orientation.HORIZONTAL);
			} else {
				layout.setOrientation(Orientation.VERTICAL);
			}
			layout.setSplitterPosition(50);
			layout.getPrimaryComponent().getElement().setAttribute("style", "width: 100%; height: 100%");
			iframe.setSizeFull();
		});
		Checkbox codeCB = new Checkbox("Show Source Code");
		codeCB.setValue(true);
		codeCB.addClassName("smallcheckbox");
		codeCB.addValueChangeListener(cb -> {
			if (cb.getValue()) {
				layout.setSplitterPosition(50);
			} else {
				layout.setSplitterPosition(100);
			}
		});
		HorizontalLayout footer = new HorizontalLayout();
		footer.setWidthFull();
		footer.setJustifyContentMode(JustifyContentMode.END);
		footer.add(codeCB, orientationCB);
		add(tabs, layout, footer);

		setSizeFull();

		tabs.addSelectedChangeListener(e -> {
			this.removeAll();
			switch (e.getSelectedTab().getLabel()) {
			case DRAGNDROP_DEMO:
				iframe.getElement().setAttribute("srcdoc", getSrcdoc(DRAGNDROP_SOURCE));
				layout.addToPrimary(new DragAndDropExportDemo());
				layout.addToSecondary(iframe);
				add(tabs, layout, footer);
				break;
			case BOTTOMTOP_DEMO:
				iframe.getElement().setAttribute("srcdoc", getSrcdoc(BOTTOMTOP_SOURCE));
				layout.addToPrimary(new BottomTopDemo());
				layout.addToSecondary(iframe);
				add(tabs, layout, footer);
				break;
			default:
				iframe.getElement().setAttribute("srcdoc", getSrcdoc(DRAGNDROP_SOURCE));
				layout.addToPrimary(new DragAndDropExportDemo());
				layout.addToSecondary(iframe);
				add(tabs, layout, footer);
				break;
			}
		});
	}

	private String getSrcdoc(String sourceUrl) {
		return "<html style=\"overflow-y:hidden; height:100%;\"><body style=\"overflow-y: scroll; height:100%;\"><script src=\"https://gist-it.appspot.com/"
				+ sourceUrl + "\"></script></body></html>";
	}
}
