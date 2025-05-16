/*-
 * #%L
 * OrgChart Add-on
 * %%
 * Copyright (C) 2017 - 2025 Flowing Code S.A.
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


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

/** @author pbartolo */
@SuppressWarnings("serial")
public class OrgChartItem implements Serializable {

  private String name;

  private String title;

  private String className;

  private Integer id;

  private List<OrgChartItem> children = new ArrayList<>();

  private Map<String, String> data;

  private boolean hybrid;

  public OrgChartItem(Integer id, String name, String title) {
    super();
    this.id = id;
    this.name = name;
    this.title = title;
  }

  public OrgChartItem(OrgChartItem original) {
    OrgChartItem orgChartCopy =
        new OrgChartItem(original.getId(), original.getName(), original.getTitle());
    for (OrgChartItem child : original.getChildren()) {
      orgChartCopy.addChildren(new OrgChartItem(child));
    }
  }

  /** Return the map of {@linkplain #setData(String, String) custom properties}. */
  public Map<String, String> getData() {
    return Optional.ofNullable(data)
        .map(Collections::unmodifiableMap)
        .orElse(Collections.emptyMap());
  }

  /**
   * Add or remove a custom property.
   *
   * @param name the name of the custom property
   * @param value the value of the custom property
   */
  public void setData(String name, String value) {
    if (data == null) {
      data = new HashMap<>();
    }
    if (value != null) {
      data.put(name, value);
    } else {
      data.remove(name);
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

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public List<OrgChartItem> getChildren() {
    return children;
  }

  public void setChildren(List<OrgChartItem> children) {
    if (this.children != children) {
      this.children = new ArrayList<>(children);
    }
  }

  public void addChildren(OrgChartItem item) {
    this.children.add(item);
  }

  /**
   * Indicates whether this item is a hybrid node.
   * A hybrid node arranges its descendant children vertically instead of
   * horizontally.
   *
   * @return {@code true} if this item is hybrid; {@code false} otherwise
   */
  public boolean isHybrid() {
    return this.hybrid;
  }

  /**
   * Sets whether this item is a hybrid node.
   * When {@code true}, the item's descendant children will be arranged
   * vertically.
   *
   * @param hybrid {@code true} to mark this node as hybrid; {@code false}
   *               otherwise
   */
  @JsonProperty("isHybrid")
  public void setHybrid(boolean hybrid) {
    this.hybrid = hybrid;
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
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    OrgChartItem other = (OrgChartItem) obj;
    if (id == null) {
      if (other.id != null) return false;
    } else if (!id.equals(other.id)) return false;
    return true;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    printChildren(this, sb, 0);
    return sb.toString();
  }

  private void printChildren(OrgChartItem item, StringBuilder sb, int count) {
    String tabs = count > 0 ? String.format("%-" + count + "s", "").replace(' ', '\t') : "";
    sb.append(tabs + item.getName() + "\n");
    count++;
    for (int i = 0; i < item.getChildren().size(); i++) {
      printChildren(item.getChildren().get(i), sb, count);
    }
  }
}
