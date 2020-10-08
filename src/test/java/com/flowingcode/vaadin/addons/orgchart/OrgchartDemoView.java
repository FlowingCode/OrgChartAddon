package com.flowingcode.vaadin.addons.orgchart;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout.Orientation;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;

@SuppressWarnings("serial")
@Route("orgchart")
@StyleSheet("./styles/orgchart/demo-styles.css")
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
		iframe.getElement().setAttribute("srcdoc", getSrcdoc(DRAGNDROP_DEMO));
		iframe.setSizeFull();
		layout.addToSecondary(iframe);

		Checkbox codeCB = new Checkbox("Show Source Code");
		codeCB.setValue(true);
		codeCB.addValueChangeListener(cb -> {
			if (cb.getValue()) {
				layout.setSplitterPosition(50);
			} else {
				layout.setSplitterPosition(100);
			}
		});

		Tabs tabs = new Tabs();
		Tab demo1 = new Tab(DRAGNDROP_DEMO);
		Tab demo2 = new Tab(BOTTOMTOP_DEMO);

		tabs.setWidthFull();
		tabs.add(demo1, demo2, codeCB);
		add(tabs, layout);
		tabs.setSelectedTab(demo1);

		setSizeFull();

		tabs.addSelectedChangeListener(e -> {
			this.removeAll();
			switch (e.getSelectedTab().getLabel()) {
			case DRAGNDROP_DEMO:
				iframe.getElement().setAttribute("srcdoc", getSrcdoc(DRAGNDROP_DEMO));
				layout.addToPrimary(new DragAndDropExportDemo());
				layout.addToSecondary(iframe);
				add(tabs, layout);
				break;
			case "Bottom to Top":
				iframe.getElement().setAttribute("srcdoc", getSrcdoc(BOTTOMTOP_DEMO));
				layout.addToPrimary(new BottomTopDemo());
				layout.addToSecondary(iframe);
				add(tabs, layout);
				break;
			default:
				iframe.getElement().setAttribute("srcdoc", getSrcdoc(DRAGNDROP_DEMO));
				layout.addToPrimary(new DragAndDropExportDemo());
				layout.addToSecondary(iframe);
				add(tabs, layout);
				break;
			}
		});
	}

	private String getSrcdoc(String demo) {
		String response;
		switch (demo) {
		case DRAGNDROP_DEMO:
			response = "<html style=\"overflow-y:hidden; height:100%;\"><body style=\"overflow-y: scroll; height:100%;\"><script src=\"https://gist-it.appspot.com/"
					+ DRAGNDROP_SOURCE + "\"></script></body></html>";
			break;
		case BOTTOMTOP_DEMO:
			response = "<html style=\"overflow-y:hidden; height:100%;\"><body style=\"overflow-y: scroll; height:100%;\"><script src=\"https://gist-it.appspot.com/"
					+ BOTTOMTOP_SOURCE + "\"></script></body></html>";
			break;
		default:
			response = "<html style=\"overflow-y:hidden; height:100%;\"><body style=\"overflow-y: scroll; height:100%;\"><script src=\"https://gist-it.appspot.com/"
					+ DRAGNDROP_SOURCE + "\"></script></body></html>";
			break;
		}
		return response;
	}
}
