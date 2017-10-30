import OrgChart from './orgchart.js';

if( document.readyState === 'complete' ) {
	console.log( 'document is already ready, just execute code here' );
	myInitCode();
} else {	
	document.addEventListener("load-org-chart", function(e) {
		console.log(e.detail); // Prints "Example of an event"
		myInitCode(e.detail.parent, e.detail.structure);
	});
}

function myInitCode(parent, structure) {
  let datascource = structure,
  orgchart = new OrgChart({
    'data' : datascource,
    'depth': 5,
    'nodeContent': 'title'
  });

  var oldorgchartnode = parent.querySelector("org-chart:first-of-type");
  if (oldorgchartnode != null)
	  oldorgchartnode.parentNode.removeChild(oldorgchartnode);

  parent.appendChild(orgchart);
  
};