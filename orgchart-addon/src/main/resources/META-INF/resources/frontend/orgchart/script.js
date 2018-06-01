import OrgChart from './orgchart.js';

if( document.readyState === 'complete' ) {
	myInitCode();
} else {	
	document.addEventListener("load-org-chart", function(e) {
		myInitCode(e.detail.parent, e.detail.structure, e.detail.chartTitleProperty,e.detail.chartNodeContentProperty,e.detail.chartNodeTitleProperty,e.detail.chartDirectionProperty,e.detail.chartZoomProperty,e.detail.chartPanProperty,e.detail.chartZoominLimitProperty,e.detail.chartZoomoutLimitProperty,e.detail.chartExportButtonProperty,e.detail.chartExportFileNameProperty,e.detail.chartExportFileExtensionProperty,e.detail.chartToggleSiblingsRespProperty,e.detail.chartDepthProperty,e.detail.chartVerticalDepthProperty,e.detail.chartExpandCollapseProperty);
	});
}

function myInitCode(parent, structure, chartTitleProperty,chartNodeContentProperty,chartNodeTitleProperty,chartDirectionProperty,chartZoomProperty,chartPanProperty,chartZoominLimitProperty,chartZoomoutLimitProperty,chartExportButtonProperty,chartExportFileNameProperty,chartExportFileExtensionProperty,chartToggleSiblingsRespProperty,chartDepthProperty,chartVerticalDepthProperty,chartExpandCollapseProperty) {
	  let datasource = structure,
	  orgchart = new OrgChart({
	    'data' : datasource,
		'nodeContent': chartNodeContentProperty,
		'nodeTitle': chartNodeTitleProperty,
		'zoom': chartZoomProperty,
		'exportButton': chartExportButtonProperty,
		'exportFileextension': chartExportFileExtensionProperty,
		'exportFilename': chartExportFileNameProperty,
		'direction': chartDirectionProperty,
		'pan': chartPanProperty,
		'zoominLimit': chartZoominLimitProperty,
		'zoomoutLimit': chartZoomoutLimitProperty,
		'depth': chartDepthProperty,
		'verticalDepth': chartVerticalDepthProperty,
		'toggleSiblingsResp': chartToggleSiblingsRespProperty
	 });
	  
	  orgchart.style["background-image"] = "none";
	  orgchart.style.width="100%";
	  orgchart.style.height="100%";
	  orgchart.style.padding="unset";
	  orgchart.style.border="unset";

	  var oldorgchartnode = parent.querySelector("org-chart:first-of-type");
	  if (oldorgchartnode != null)
		{
		  	oldorgchartnode.parentNode.removeChild(oldorgchartnode);
		}

	  parent.appendChild(orgchart);
	  
	};



