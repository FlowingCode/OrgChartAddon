package com.flowingcode.vaadin.addons.orgchart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * 
 * @author pbartolo
 *
 */
@SuppressWarnings("serial")
public class OrgChartItem implements Serializable {

	private String name;
	
	private String title;
	
	private Integer nodeId;
		
	private List<OrgChartItem> children = null;
	
	public OrgChartItem(Integer nodeId, String name, String title) {
		super();
		this.nodeId = nodeId;
		this.name = name;
		this.title = title;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getNodeId() {
		return nodeId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<OrgChartItem> getChildren() {
		return children;
	}

	public void setChildren(List<OrgChartItem> children) {
		this.children = children;
	}
	
	public void addChild(OrgChartItem item) {
		if (this.children==null)
			children = new ArrayList<OrgChartItem>();
		children.add(item);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nodeId == null) ? 0 : nodeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrgChartItem other = (OrgChartItem) obj;
		if (nodeId == null) {
			if (other.nodeId != null)
				return false;
		} else if (!nodeId.equals(other.nodeId))
			return false;
		return true;
	}	
	
}
