window.com_flowingcode_vaadin_orgchart_OrgChart =
function() {
    
    var connector = this;
    var element = connector.getElement();
    var state = connector.getState();
   
    $(element).html('<div id="chart-container"/>');
    
    connector.onStateChange = function() {       
        var value = state.value;
        
        let exportChart = state.chartExportButton;
        let exportExt = state.chartExportFileExtension;
        if(exportChart & exportExt == "pdf"){
        	addJSfile("https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.debug.js");
        }

        let orgchart = $("#chart-container", element).orgchart({
        	'data' : jQuery.parseJSON(value),
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
        	'depth': state.chartDepth,
        	'verticalDepth': state.chartVerticalDepth,
        	'toggleSiblingsResp': state.chartToggleSiblingsResp
        });  
        
        var title = state.chartTitle;
        if(title) {
        	$(element).prepend('<p>' + title + '</p>');
        }
  		
  		$(element).find(".orgchart").css("background-image", "none");
    }
    	
};

function addJSfile(src) {
    var jsfile = $("<script type='text/javascript' src='"+src+"'>");
    $("head").append(jsfile); 
}; 



