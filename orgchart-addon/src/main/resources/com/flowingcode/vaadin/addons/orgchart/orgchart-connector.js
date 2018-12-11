/*-
 * #%L
 * OrgChart Add-on
 * %%
 * Copyright (C) 2017 - 2018 FlowingCode S.A.
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
window.com_flowingcode_vaadin_addons_orgchart_OrgChart =
function() {
    
    var connector = this;
    var element = connector.getElement();
    var state = connector.getState();
    var rpcProxy = this.getRpcProxy();
    
    connector.onStateChange = function() {       
        var value = state.value;     
                        
        let exportChart = state.chartExportButton;
        let exportExt = state.chartExportFileExtension;
        if(exportChart & exportExt == "pdf"){
        	addJSfile("https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.debug.js");
        }
        if(exportChart & isIEBrowser() > 0){
        	addJSfile("https://cdn.jsdelivr.net/npm/es6-promise@4/dist/es6-promise.auto.js");
        }

        var nodeTemplate;
        if (state.nodeTemplate && state.nodeTemplateParam) {
        	nodeTemplate = Function(state.nodeTemplateParam, state.nodeTemplate);
        }
        
        var jsonData = jQuery.parseJSON(value)
               
        var orgchart = $(element).orgchart({
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
    	        	rpcProxy.onNodeClick(data.id); 
    	        });
    	      }
        });  
                
        // add title
        var title = state.chartTitle;
        if(title) {
        	$(element).prepend('<p style="color: black;font-weight: bold;">' + title + '</p>');
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
        	$(element).find(".orgchart").addClass('noncollapsable');
        } else {
        	$(element).find(".orgchart").removeClass('noncollapsable');
        }
        
        $(element).find(".orgchart").css("background-image", "none");  		
  		$("div.orgchart").prev().closest("div").attr("id", "chart-container");  	
        
        // if draggable 
  		var draggable = state.chartDraggable;
  	  		
  		if(draggable){
  			orgchart.$chart.on('nodedrop.orgchart', function(event, extraParams) {
  				  				
  				let draggedNode = extraParams.draggedNode.attr('id');
  				let dragZone = extraParams.dragZone.attr('id');
  				let dropZone = extraParams.dropZone.attr('id');
  				  				
  				rpcProxy.updateChart(draggedNode, dragZone, dropZone);  				
  			  });  				
  		} 			
  		
    }
    	
};

function addJSfile(src) {
    var jsfile = $("<script type='text/javascript' src='"+src+"'>");
    $("head").append(jsfile); 
}; 

function isIEBrowser() {
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


