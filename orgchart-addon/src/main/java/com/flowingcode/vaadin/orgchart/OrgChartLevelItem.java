package com.flowingcode.vaadin.orgchart;

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
public class OrgChartLevelItem implements Serializable {

	private String name;
	
	private String title;
	
	private Integer levelId;
	
	private List<OrgChartLevelItem> children = new ArrayList<>();
	
	public OrgChartLevelItem(Integer levelId, String name, String title) {
		super();
		this.levelId = levelId;
		this.name = name;
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<OrgChartLevelItem> getChildren() {
		return children;
	}

	public void setChildren(List<OrgChartLevelItem> children) {
		this.children = children;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((levelId == null) ? 0 : levelId.hashCode());
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
		OrgChartLevelItem other = (OrgChartLevelItem) obj;
		if (levelId == null) {
			if (other.levelId != null)
				return false;
		} else if (!levelId.equals(other.levelId))
			return false;
		return true;
	}			
	
}
