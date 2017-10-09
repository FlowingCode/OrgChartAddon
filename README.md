# OrgChart Add-On for Vaadin 7.7.x

OrgChart Add-On is a Vaadin integration with the JQuery library [OrgChart](https://github.com/dabeng/OrgChart).

## Features

- Supported library features:
	- data (json), 
    - pan, 
    - zoom,
    - zoominLimit, 
    - zoomoutLimit, 
    - direction, 
    - verticalDepth, 
    - depth, 
    - toggleSiblingsResp, 
    - nodeTitle, 
    - nodeContent, 
    - exportButton, 
    - exportFilename, 
    - exportFileextension (png & pdf)
- Enable/disable exapand/collapse feature
- Add a chart title

## Online demo

Not available yet.

## Download release

Official releases of this add-on will be available at Vaadin Directory soon.

## Building and running demo

>git clone repository
>mvn clean install
>cd demo
>mvn jetty:run

To see the demo, navigate to http://localhost:8080/

## Release notes

### Version 1.0-SNAPSHOT
- Initial Version. This version allows to visualize an organizational chart and exported as a png or a pdf file. 

## Roadmap

This component is developed as a hobby with no public roadmap or any guarantees of upcoming releases. That said, the following features are planned for upcoming releases:

- Color changing of nodes and chart in general
- Edition of the organization chart 
- Drag and drop of nodes

## Issue tracking

The issues for this add-on are tracked on its github.com page. All bug reports and feature requests are appreciated. 

## Contributions

Contributions are welcome, but there are no guarantees that they are accepted as such. Process for contributing is the following:

- Fork this project
- Create an issue to this project about the contribution (bug or feature) if there is no such issue about it already. Try to keep the scope minimal.
- Develop and test the fix or functionality carefully. Only include minimum amount of code needed to fix the issue.
- Refer to the fixed issue in commit
- Send a pull request for the original project
- Comment on the original issue that you have implemented a fix for it

## License & Author

Add-on is distributed under Apache License 2.0. For license terms, see LICENSE.txt.

OrgChart Add-On is written by Paola De Bartolo

# Developer Guide

## Getting started

Here is a simple example on how to try out the add-on component:

```java
  OrgChartItem item1 = new OrgChartItem(1, "Root item", "Root Level");
  OrgChartItem item2 = new OrgChartItem(2, "Item 2", "Level 1");
  OrgChartItem item3 = new OrgChartItem(3, "Item 3", "Level 1");        
  item1.setChildren(Arrays.asList(item2, item3));
  
  OrgChart orgChart = new OrgChart(item1);
  orgChart.setChartTitle("A Ttile");    
  orgChart.setChartNodeContent("title");
  orgChart.setChartExportButton(true);
  orgChart.setZoom(true);
  
  // add to a layout
  VerticalLayout layout = new VerticalLayout();
  layout.addComponent(orgChart);
```


