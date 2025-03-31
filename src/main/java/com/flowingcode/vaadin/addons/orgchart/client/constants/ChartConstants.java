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
package com.flowingcode.vaadin.addons.orgchart.client.constants;


public interface ChartConstants {

  public static String DEFAULT_CHART_EXPORT_FILENAME = "OrgChart";

  public static String CHART_EXPORT_EXTENSION_PNG = "png"; // default value in the library

  public static String CHART_EXPORT_EXTENSION_PDF = "pdf";

  public static Double CHART_ZOOM_IN_LIMIT_DEFAULT = 7.0;

  public static Double CHART_ZOOM_OUT_LIMIT_DEFAULT = 0.5;

  public static Integer CHART_DEPTH_DEFAULT = 999;

  public static String CHART_NODE_TITLE_DEFAULT = "name";

  public static String CHART_NODE_ID_DEFAULT = "id";
}
