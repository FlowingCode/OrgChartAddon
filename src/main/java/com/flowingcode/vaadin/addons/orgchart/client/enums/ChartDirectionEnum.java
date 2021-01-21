package com.flowingcode.vaadin.addons.orgchart.client.enums;

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


/**
 * Enumeration of the directions that the organization chart can have. <br>
 * Default one is "t2b = Top to Bottom".
 * 
 * @author pbartolo
 *
 */
public enum ChartDirectionEnum {
	
	TOP_TO_BOTTOM("t2b"), BOTTOM_TO_TOP("b2t"), LEFT_TO_RIGHT("l2r"), RIGHT_TO_LEFT("r2l");
		
	private String abreviation;
	
	ChartDirectionEnum(String abreviation) {
		this.abreviation = abreviation;
	}

	public String getAbreviation() {
		return abreviation;
	}

}
