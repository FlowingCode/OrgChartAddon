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
public class OrgChartItem implements Serializable{

	private String name;
	
	private String title;
	
	private Integer id;
		
	private List<OrgChartItem> children = new ArrayList<>();
	
	public OrgChartItem(Integer id, String name, String title) {
		super();
		this.id = id;
		this.name = name;
		this.title = title;
	}
	
	public OrgChartItem(OrgChartItem original) {
		OrgChartItem orgChartCopy = new OrgChartItem(original.getId(), original.getName(), original.getTitle());
		for(OrgChartItem child : original.getChildren()) {
			orgChartCopy.addChildren(new OrgChartItem(child));
		} 
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	public void addChildren(OrgChartItem item) {
		this.children.add(item);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	

}
