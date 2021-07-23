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
import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import jQuery from "jquery";
import html2canvas from 'html2canvas';

/**
 * `fc-orgchart`
 *
 * OrgChart element.
 *
 * @customElement
 * @polymer
 */
class FCOrgChart extends PolymerElement {

	initializeOrgChart(statestring,data,identifier) {
        var $ = window.jQuery || jQuery;
        var state = $.parseJSON(statestring);
		
        let exportChart = state.chartExportButton;
        let exportExt = state.chartExportFileExtension;
        if(exportChart & exportExt == "pdf"){
        	var src = "https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.debug.js";
    	    var jsfile = $("<script type='text/javascript' src='"+src+"'>");
    	    $("head").append(jsfile); 
        }
        if(exportChart & this.isIEBrowser() > 0){
        	var src = "https://cdn.jsdelivr.net/npm/es6-promise@4/dist/es6-promise.auto.js";
    	    var jsfile = $("<script type='text/javascript' src='"+src+"'>");
    	    $("head").append(jsfile); 
        }

        var nodeTemplate;
        if (state.nodeTemplate && state.nodeTemplateParam) {
        	nodeTemplate = Function(state.nodeTemplateParam, state.nodeTemplate);
        }

        var jsonData = $.parseJSON(data);
        
        var currOrgChart = this;
        
        var orgchart = $('#' + identifier).orgchart({
        	'data' : jsonData,
        	'nodeContent': state.chartNodeContent,
        	'nodeTitle': state.chartNodeTitle,
        	'zoom': state.chartZoom,
        	'exportButton': state.chartExportButton,
        	'exportFileextension': state.chartExportFileExtension,
        	'exportFilename': state.chartExportFileName,
        	'direction': state.chartDirection,
        	'pan': state.chartPan,
        	'zoominLimit': state.chartZoominLimit,
        	'zoomoutLimit': state.chartZoomoutLimit,
        	'visibleLevel': state.chartDepth,
        	'verticalLevel': state.chartVerticalDepth,
        	'toggleSiblingsResp': state.chartToggleSiblingsResp,
        	'draggable': state.chartDraggable,
        	'nodeId': state.chartNodeId,
        	'nodeTemplate': nodeTemplate,
        	'createNode': function($node, data) {
    	        $node.on('click', function() {
    	        	currOrgChart.$server.onClick(data.id.toString());
    	        });
    	        
    	        $node.on('mouseover', function(event) {
    	            var target = event.target;
    	            if (!$(target).attr('title')) {
    	                if ($(target).prop('scrollWidth') > $(target).prop('clientWidth')) {
    	                    $(target).attr('title', target.innerText);
    	                }
    	            }
    	        });    	        
    	        
    	      }
        });  
        
        window.html2canvas = html2canvas;

        // add title
        var title = state.chartTitle;
        if(title) {
        	$('#' + identifier).prepend('<p style="color: black;font-weight: bold;">' + title + '</p>');
        } 		 
        
        /*
         * Users can enable/disable expand/collapse feature with className "noncollapsable".
         * 
         * $('.orgchart').addClass('noncollapsable'); -> to deactivate	  
         * $('.orgchart').removeClass('noncollapsable'); -> to activate
         * 
         */
        var chartExpandCollapse = state.chartExpandCollapse;
        if(chartExpandCollapse){
        	this.querySelector(".orgchart").classList.add("noncollapsable")
        } else {
        	this.querySelector(".orgchart").classList.remove("noncollapsable")
        }
        
  		$("div.orgchart").prev().closest("div").attr("id", "chart-container");
  		this.querySelector(".orgchart").style.setProperty("background-image","none");
  		
  		// workaround  for direction b2t with node template  without content div
  		var direction = state.chartDirection;
  		var nodeTemplate = nodeTemplate;
  		if(direction && direction == "b2t" && nodeTemplate){
  			 this.querySelectorAll(".node").forEach(node => {
  				 var title = node.querySelector(".title");
  				 var content = node.querySelector(".content");
  				 if(title && !content){
  					  title.style.transformOrigin = "center";
  				 }  				
  			 });	
  		}  		
        
        // if draggable 
  		var draggable = state.chartDraggable;
  	  		
  		if(draggable){
  			orgchart.$chart.on('nodedrop.orgchart', function(event, extraParams) {
  				  				
  				let draggedNode = extraParams.draggedNode.attr('id');
  				let dragZone = extraParams.dragZone.attr('id');
  				let dropZone = extraParams.dropZone.attr('id');
  				  				
  				currOrgChart.$server.updateDraggedNode(draggedNode, dragZone, dropZone);
  			  });  				
  		} 			
		
	}
		
	isIEBrowser() {
		  var sAgent = window.navigator.userAgent;
		  var Idx = sAgent.indexOf("MSIE");

		  // If IE, return version number.
		  if (Idx > 0) 
		    return parseInt(sAgent.substring(Idx+ 5, sAgent.indexOf(".", Idx)));

		  // If IE 11 then look for Updated user agent string.
		  else if (!!navigator.userAgent.match(/Trident\/7\./)) 
		    return 11;

		  else
		    return 0; //It is not IE
		};
	
    static get template() {
        return html`
            <style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                }
            </style>
            <slot/>
        `;
    }

    static get is() {
        return 'fc-orgchart';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(FCOrgChart.is, FCOrgChart);
