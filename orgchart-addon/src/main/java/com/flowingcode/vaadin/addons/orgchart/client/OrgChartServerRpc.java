package com.flowingcode.vaadin.addons.orgchart.client;

import com.vaadin.shared.communication.ServerRpc;

public interface OrgChartServerRpc extends ServerRpc {

	void updateChart(String draggedNode, String dragZone, String dropZone);
	
}
